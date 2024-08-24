package my.bt.zara.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "issues")
@Getter
public class Issue {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String project;
  private String issueType;
  private String title;
  private String description;

  public void updateIssue(Issue issue) {
    this.project = issue.project;
    this.issueType = issue.issueType;
    this.title = issue.title;
    this.description = issue.description;
  }
}
