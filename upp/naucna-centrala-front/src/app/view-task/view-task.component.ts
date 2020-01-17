import { Component, OnInit } from '@angular/core';
import { TaskService } from '../service/task.service';

@Component({
  selector: 'app-view-task',
  templateUrl: './view-task.component.html',
  styleUrls: ['./view-task.component.css']
})
export class ViewTaskComponent implements OnInit {

  tasks: any; //task_id, name, assignee
  no: number = 0;

  constructor(private taskService : TaskService) { }

  ngOnInit() {
    this.getTasks();
  }

  getTasks(){
    this.taskService.getMyTask().subscribe(
      data => this.tasks = data
    )
  }

}
