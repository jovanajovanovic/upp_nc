package com.upp.naucnacentrala.dto;

public class TaskDto {
    String taskId;
    String name;
    String assignee;
    String name_id;

    public TaskDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TaskDto(String taskId, String name, String assignee, String name_id) {
        super();
        this.taskId = taskId;
        this.name = name;
        this.assignee = assignee;
        this.name_id = name_id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getName_id() {
        return name_id;
    }

    public void setName_id(String name_id) {
        this.name_id = name_id;
    }
}
