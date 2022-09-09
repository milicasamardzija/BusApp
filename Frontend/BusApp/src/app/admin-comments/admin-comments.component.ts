import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { CommentService } from '../comments/comment.service';

@Component({
  selector: 'app-admin-comments',
  templateUrl: './admin-comments.component.html',
  styleUrls: ['./admin-comments.component.css']
})
export class AdminCommentsComponent implements OnInit {
  comments!: any[];

  constructor(private commentsService: CommentService) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll(){
    this.commentsService.getAll().subscribe(
      response => {
        this.comments = response;
      }
    )
  }

  approve(id: number){
    this.commentsService.approve(id).subscribe(
      response => {
        Swal.fire('Uspesno!', 'Uspesno ste se odobrili komentar.', 'success');
        this.getAll();
      }
    )
  }

  reject(id: number){
    this.commentsService.reject(id).subscribe(
      response => {
        Swal.fire('Uspesno!', 'Uspesno ste se odbili komentar.', 'success');
        this.getAll();
      }
    )
  }

}
