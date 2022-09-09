import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { CommentService } from '../comments/comment.service';
import { Comment } from '../model/Comment';

@Component({
  selector: 'app-comment-add',
  templateUrl: './comment-add.component.html',
  styleUrls: ['./comment-add.component.css']
})
export class CommentAddComponent implements OnInit {
  text!: string;

  constructor(private commentService: CommentService, public dialogRef: MatDialogRef<CommentAddComponent>) { }

  ngOnInit(): void {
  }

  add(){
    this.commentService.add(this.text).subscribe(
      response => {
        this.dialogRef.close({
          data: "updated"
        })
      }
    )
  }

  cancel(){
    this.dialogRef.close();
  }

}
