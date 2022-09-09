import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CommentAddComponent } from '../comment-add/comment-add.component';
import { Comment } from '../model/Comment';
import { CommentService } from './comment.service';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {
  comments!: Comment[];
  role!: string

  constructor(private commentService: CommentService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.role = localStorage.getItem('role') || "";
    this.getAll();
  }

  getAll(){
    this.commentService.getAll().subscribe(
      response => {
        this.comments = response;
      }
    )
  }

  add(){
    const dialogRef = this.dialog.open(CommentAddComponent, {
      width: '360px',
      height: '250px'
    });
    
    dialogRef.afterClosed().subscribe(
      respoonse => {
        if (respoonse.data == "updated"){
          this.getAll();
        }
      }
    )
  }

}
