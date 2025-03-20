package com.onlinebookreadingapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebookreadingapp.entity.Publisher;
import com.onlinebookreadingapp.serviceImpl.PublisherServiceImpl;

@RestController
@RequestMapping("/publisher/v1")
@CrossOrigin(origins = { "*" })
public class PublisherController {

  private final PublisherServiceImpl publisherServiceImpl;

  public PublisherController(PublisherServiceImpl publisherServiceImpl) {
    this.publisherServiceImpl = publisherServiceImpl;
  }

  @PostMapping("/addPublisher")
  public ResponseEntity<Publisher> addPublisher(@RequestBody Publisher publisher) {
    return ResponseEntity.ok(publisherServiceImpl.addPublisher(publisher));
  }

  @GetMapping("/getPublisherByPublisherId/{publisherId}")
  public ResponseEntity<Publisher> getPublisherByPublisherId(@PathVariable(value = "publisherId") Long publisherId) {
    return ResponseEntity.ok(publisherServiceImpl.getPublisherByPublisherId(publisherId));
  }

  @GetMapping("/getPublisherByPublisherName/{publisherName}")
  public ResponseEntity<Publisher> getPublisherByPublisherName(
      @PathVariable(value = "publisherName") String publisherName) {
    return ResponseEntity.ok(publisherServiceImpl.getPublisherByPublisherName(publisherName));
  }

  @DeleteMapping("/deletePublisherByPublisherId/{publisherId}")
  public ResponseEntity<Publisher> deletePublisherByPublisherId(@PathVariable(value = "publisherId") Long publisherId) {
    return ResponseEntity.ok(publisherServiceImpl.deletePublisherByPublisherId(publisherId));
  }

  @DeleteMapping("/deletePublisherByPublisherName/{publisherName}")
  public ResponseEntity<Publisher> deletePublisherByPublisherName(
      @PathVariable(value = "publisherName") String publisherName) {
    return ResponseEntity.ok(publisherServiceImpl.deletePublisherByPublisherName(publisherName));
  }

  @GetMapping("/getAllPublishers")
  public ResponseEntity<List<Publisher>> getAllPublishers() {
    return ResponseEntity.ok(publisherServiceImpl.getAllPublishers());
  }

  @PutMapping("/updatePublisherByPublisherId/{publisherId}")
  public ResponseEntity<Publisher> updatePublisherByPublisherId(@PathVariable(value = "publisherId") Long publisherId,
      @RequestBody Publisher publisher) {
    return ResponseEntity.ok(publisherServiceImpl.updatePublisherByPublisherId(publisherId, publisher));
  }

}
