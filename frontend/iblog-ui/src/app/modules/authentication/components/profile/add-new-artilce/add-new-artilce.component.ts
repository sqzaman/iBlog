import { Component, OnInit } from '@angular/core';
import { Article } from '../model/article';
import { ArticleService } from '../../../services/article.service';


@Component({
  selector: 'app-add-new-artilce',
  templateUrl: './add-new-artilce.component.html',
  styleUrls: ['./add-new-artilce.component.css']
})
export class AddNewArtilceComponent implements OnInit {

  article: Article = new Article();
  submitted: boolean = false;
  success: boolean = false;
  failed: boolean = false;
  message: string = '';
  constructor(private articleService: ArticleService) { }


  ngOnInit() {
  }

  newArticle(): void {
    this.submitted = false;
    this.article = new Article();
  }
 
  save() {
    this.articleService.createArticle(this.article)
      .subscribe((data) => {
        console.log(data);
        this.success = JSON.parse(JSON.stringify(data)).ok;
        this.message = JSON.parse(JSON.stringify(data)).message;
        this.article = new Article();
      }, (error) => {
      //  this.response = new Response(error);
       // this.response = error;
        //console.log(error.error.errors);

        this.failed = !JSON.parse(JSON.stringify(error.error)).ok;

        error.error.errors.forEach(element => {
          this.message += element.defaultMessage + '<br/>';
        });
        //this.message = JSON.parse(JSON.stringify(error.error)).message;
      });
     // console.log("========================");
      //console.log( this.errors);
     // console.log("========================");
      if (this.success) {
        this.submitted = true;
        this.article = new Article();
      }
  }
  onSubmit() {
    //this.submitted = true;
    this.success = false;
    this.failed = false;
    this.message = '';
    this.save();
  }

}
