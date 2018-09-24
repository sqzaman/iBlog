import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Globals } from '../../../shared/config/globals.service';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ViewArticleService {

  constructor(private http: HttpClient, private globals: Globals) {
  }

  getArticles(articleId): Observable<Object> {
    return this.http.get(this.globals.BASE_API_URL + 'blog/get/' + articleId);
  }

  insertComment(comment, articleId): Observable<Object> {
    return this.http.post(this.globals.BASE_API_URL + 'blog/add-comment/' + articleId, comment);
  }

  getComments(articleId): Observable<Object> { 
    return this.http.get(this.globals.BASE_API_URL + 'blog/get/comments/' + articleId);
  }


}
