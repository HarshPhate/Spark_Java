package com.example.search_service.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class FileServiceClientFallBack
        implements FileSearchClient {

    private static final Logger log =
            LoggerFactory.getLogger(
                    FileServiceClientFallBack.class
            );

    @Override
    public List<Map<String, Object>> getAllFiles() {

        log.warn("Fallback for getAllFiles");

        return List.of();
    }

    @Override
    public List<Map<String, Object>>
    getAllFilesByFolderId(Long folderId) {

        log.warn(
                "Fallback for folderId {}",
                folderId
        );

        return List.of();
    }
}