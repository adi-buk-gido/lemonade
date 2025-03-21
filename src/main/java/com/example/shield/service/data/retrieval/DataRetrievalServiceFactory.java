package com.example.shield.service.data.retrieval;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.shield.model.file.SourceType;

import software.amazon.awssdk.utils.ImmutableMap;

@Component
public class DataRetrievalServiceFactory {

    private static Map<SourceType, IDataRetrievalService> serviceMap;
    
        @Autowired
        private S3DataService s3DataService;
    
        @Autowired
        private SFTPDataService sftpDataService;
    
        @Autowired
        private LocalDataService localDataService;
    
    
        @PostConstruct
        public void init(){
            DataRetrievalServiceFactory.serviceMap = ImmutableMap.of(
                    SourceType.S3, s3DataService,
                    SourceType.SFTP, sftpDataService,
                    SourceType.LOCAL, localDataService
            );
        }
    
        public IDataRetrievalService getService(SourceType sourceType) {
            return serviceMap.get(sourceType);
    }

}
