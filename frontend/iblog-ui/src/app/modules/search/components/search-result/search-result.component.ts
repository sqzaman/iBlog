import { Component, OnInit } from '@angular/core';
import { SearchService } from '../../services/search.service';
import { ActivatedRoute } from "@angular/router";
import { Search } from '../../models/search';
import { Globals } from '../../../../shared/config/globals.service';


@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css']
})
export class SearchResultComponent implements OnInit {


  searchResult : Object;
  search: Search = new Search();
  currentUrl: string;


  constructor(private searchService: SearchService, public route: ActivatedRoute, private globals: Globals) { 
   // console.log(this.route.parent.url);
   // console.log(this.route.snapshot.queryParamMap);
    this.search.text = this.route.snapshot.queryParamMap.get('text');
  }

  ngOnInit() {
    this.searchService.getArticles(this.search.text).subscribe(
      (data) => { this.searchResult = data
      //console.log(this.searchResult);
      }
    )
  }

  onSubmit() {
    let url = this.globals.BASE_URL + "posts?text=" + this.search.text;
    history.pushState(null, null, url); ;
    this.searchService.getArticles(this.search.text).subscribe(
      (data) => { this.searchResult = data
     // console.log(this.searchResult);
      }
    )

  }

  showMore(currentTotal: number){
    //console.log(currentTotal);
    this.searchService.getArticles(this.search.text, currentTotal+10).subscribe(
      (data) => { this.searchResult = data
      //console.log(this.searchResult);
      }
    )
  }

}
