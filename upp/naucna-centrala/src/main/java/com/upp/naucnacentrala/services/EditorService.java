package com.upp.naucnacentrala.services;

import com.upp.naucnacentrala.dto.EditorReviewerDto;
import com.upp.naucnacentrala.exceptions.ObjectNotFound;
import com.upp.naucnacentrala.model.Editor;

import java.util.List;

public interface EditorService {

    List<EditorReviewerDto> getEditors(List<String> scientifics);

    List<String> getEditorsByCode(Object value) throws ObjectNotFound;
}
