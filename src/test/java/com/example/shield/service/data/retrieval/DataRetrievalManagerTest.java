package com.example.shield.service.data.retrieval;

import com.example.shield.dao.DataFilesDaoImp;
import com.example.shield.model.conversation.RoomConversation;
import com.example.shield.model.file.FileProcessStatus;
import com.example.shield.model.file.InputMetadata;
import com.example.shield.model.file.SourceType;
import com.example.shield.service.kafka.KafkaProducerService;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;



// @RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class DataRetrievalManagerTest {

    @Mock
    private DataRetrievalServiceFactory dataRetrievalServiceFactory;

    @Mock
    private IDataRetrievalService dataRetrievalService;

    @Mock
    private DataFilesDaoImp dataFilesDaoImp;

    @Mock
    private KafkaProducerService kafkaProducerService;

    @InjectMocks
    private DataRetrievalManager dataRetrievalManager = new DataRetrievalManager();

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testProcessData_NewFile_Success() throws Exception {
        // Given
        InputMetadata inputMetadata = new InputMetadata();
        inputMetadata.setInputId("test-file");
        inputMetadata.setSourceType(SourceType.S3);
        inputMetadata.setInputFormat("XML");

        when(dataFilesDaoImp.getFileRetStatus(anyString())).thenReturn(null);
        doReturn(mock(InputStream.class)).when(dataRetrievalService).retrieveData(any());
        doReturn(new RoomConversation()).when(dataRetrievalService).convertData(any(), any());
        when(dataRetrievalServiceFactory.getService(any(SourceType.class))).thenReturn(dataRetrievalService);
        doNothing().when(dataRetrievalService).connect();
        doNothing().when(dataRetrievalService).disconnect();
        // When
        dataRetrievalManager.processData(inputMetadata);

        // Then
        verify(dataRetrievalService, times(1)).connect();
        verify(dataRetrievalService, times(1)).retrieveData("test-file");
        verify(dataRetrievalService, times(1)).convertData(any(), eq("XML"));
        verify(dataRetrievalService, times(1)).disconnect();
        verify(kafkaProducerService, times(1)).sendDataToEnrichment(any(RoomConversation.class));
        verify(dataFilesDaoImp, times(1)).setStatus("test-file", FileProcessStatus.INITIATED);
        verify(dataFilesDaoImp, times(1)).setStatus("test-file", FileProcessStatus.COMPLETED);
    }

    @Test
    public void testProcessData_AlreadyProcessedFile() throws Exception {
        // Given
        InputMetadata inputMetadata = new InputMetadata();
        inputMetadata.setInputId("processed-file");
        inputMetadata.setSourceType(SourceType.S3);

        when(dataRetrievalServiceFactory.getService(any(SourceType.class))).thenReturn(dataRetrievalService);
        when(dataFilesDaoImp.getFileRetStatus("processed-file")).thenReturn(FileProcessStatus.COMPLETED);

        // When
        dataRetrievalManager.processData(inputMetadata);

        // Then
        verify(dataRetrievalService, never()).connect();
        verify(dataRetrievalService, never()).retrieveData(anyString());
        verify(kafkaProducerService, never()).sendDataToEnrichment(any(RoomConversation.class));
    }

    @Test
    public void testProcessData_ErrorHandling() {
        // Given
        InputMetadata inputMetadata = new InputMetadata();
        inputMetadata.setInputId("error-file");
        inputMetadata.setSourceType(SourceType.S3);
        inputMetadata.setInputFormat("XML");

        when(dataRetrievalServiceFactory.getService(any(SourceType.class))).thenReturn(dataRetrievalService);
        when(dataFilesDaoImp.getFileRetStatus("error-file")).thenReturn(null);
        when(dataRetrievalService.retrieveData("error-file")).thenThrow(new RuntimeException("Connection failed"));
        doNothing().when(dataRetrievalService).connect();

        // When & Then
        Exception exception = assertThrows(Exception.class, () -> {
            dataRetrievalManager.processData(inputMetadata);
        });

        assertTrue(exception.getMessage().contains("Connection failed"));

        verify(dataFilesDaoImp, times(1)).setStatus("error-file", FileProcessStatus.INITIATED);
        verify(dataRetrievalService, times(1)).connect();
        verify(dataRetrievalService, times(1)).retrieveData("error-file");
        verify(kafkaProducerService, never()).sendDataToEnrichment(any(RoomConversation.class));
    }
}

