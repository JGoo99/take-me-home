package com.example.takemehome.data.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@ToString
public class RankingPageDto {
  private int pageNum;
  private int size;
  private int startPage;
  private int endPage;
  private int buttonLimit;

  public RankingPageDto() {
    this.pageNum = 1;
    this.size = 7;
    this.buttonLimit = 3;
  }

  public void setPaging(Pageable pageable, int totalPage) {
    int startPageTmp =
      (((int) Math.ceil(((double) pageable.getPageNumber() / this.buttonLimit))) - 1) * this.buttonLimit + 1;

    this.startPage = Math.max(1, startPageTmp);
    this.endPage =  Math.min((startPage + this.buttonLimit - 1), totalPage);
  }
}
