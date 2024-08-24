package my.bt.zara.controller;

import my.bt.zara.model.Issue;
import my.bt.zara.service.IssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

  private final IssueService issueService;

  public IssueController(IssueService issueService) {
    this.issueService = issueService;
  }

  @PostMapping
  public ResponseEntity<Issue> createIssue(@RequestBody Issue issue) {
    Issue createdIssue = issueService.createIssue(issue);
    return ResponseEntity.ok(createdIssue);
  }

  @GetMapping
  public ResponseEntity<List<Issue>> getAllIssues() {
    List<Issue> issues = issueService.getAllIssues();
    return ResponseEntity.ok(issues);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Issue> getIssueById(@PathVariable Long id) {
    Issue issue = issueService.getIssueById(id);
    return ResponseEntity.ok(issue);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Issue> updateIssue(@PathVariable Long id, @RequestBody Issue issueDetails) {
    Issue updatedIssue = issueService.updateIssue(id, issueDetails);
    return ResponseEntity.ok(updatedIssue);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteIssue(@PathVariable Long id) {
    issueService.deleteIssue(id);
    return ResponseEntity.noContent().build();
  }
}

