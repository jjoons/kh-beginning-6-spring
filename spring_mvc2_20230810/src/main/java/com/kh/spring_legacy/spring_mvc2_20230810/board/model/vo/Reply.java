package com.kh.spring_legacy.spring_mvc2_20230810.board.model.vo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
  private int no;
  private int boardNo;
  private int writerNo;
  private String writerId;
  private String content;
  private Date createDate;
  private Date modifyDate;
}