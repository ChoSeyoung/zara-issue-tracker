package my.bt.zara.service;

import my.bt.zara.model.Issue;
import my.bt.zara.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {

  private final IssueRepository issueRepository;

  public IssueService(IssueRepository issueRepository) {
    this.issueRepository = issueRepository;
  }

  public Issue createIssue(Issue issue) {
    return issueRepository.save(issue);
  }

  public List<Issue> getAllIssues() {
    return issueRepository.findAll();
  }

  public Issue getIssueById(Long id) {
    return issueRepository.findById(id).orElseThrow(() -> new RuntimeException("Issue not found"));
  }

  public Issue updateIssue(Long id, Issue issueDetails) {
    Issue issue = getIssueById(id);
    issue.updateIssue(issueDetails);
    return issueRepository.save(issue);
  }

  public void deleteIssue(Long id) {
    issueRepository.deleteById(id);
  }
}

