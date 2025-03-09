package com.onlinebookreadingapp.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publisherId;
    
    private String publisherName;
    private String publisherMobileNumber;
    private String publisherEmail;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude  // Prevents infinite recursion
    @EqualsAndHashCode.Exclude  // Prevents hashCode issues
    private List<Book> publisherBooks;
}
