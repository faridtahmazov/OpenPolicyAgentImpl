package com.company.service;

import com.company.entity.Document;
import com.company.repo.DocumentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service @RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepo documentRepo;

    public void save(Document document){
        this.documentRepo.save(document);
    }

    @PostAuthorize("@opaClient.allow('read', T(java.util.Map).of('type', 'document', 'owner', returnObject.owner))")
    public Document findById(Long id){
        return documentRepo.findById(id).orElseThrow(()-> new ResponseStatusException(NOT_FOUND));
    }
}
