package com.upp.naucnacentrala.services;

import com.upp.naucnacentrala.dto.EditorReviewerDto;
import com.upp.naucnacentrala.model.Editor;

import java.util.List;

public interface EditorService {

    List<EditorReviewerDto> getEditors(List<String> scientifics);
}
