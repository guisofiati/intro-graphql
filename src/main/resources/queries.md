# Queries:

---

## Author

### Find all authors:
```
 query {
    findAllAuthors {
        id
        firstName
        lastName
    }
 }
```

---

### Find author by id:
```
query {
    findAuthorById(id:"some-id") {
        id firstName lastName
    }
}
```

---

### Insert new author:
```
mutation {
    insertAuthor(firstName:"Bar", lastName:"Foo") {
        id, firstName, lastName
    }
}
```

---

### Update author:
- Fields are optional except **id**
```
mutation {
    updateAuthor(id: "some-id", firstName: "Margaret", lastName: "Berny") {
        id 
        firstName 
        lastName
     }
 }
```

---

### Delete author:
- Cannot delete if there's books registered with this author
```
mutation {
    deleteAuthor(id:"some-id")
}
```

___

## Book:

### Find all books:
```
query {
    findAllBooks {
    id
    name
    pages
    author {
      id
      firstName
      lastName
    }
  }
}
```

---

### Find book by id:
```
query {
    findBookById(id:"some-id") {
        id
        name
        pages
        author {
            id
            firstName
            lastName
        }
    }
}
```

---

### Insert new book:
```
mutation {
  insertBook(name:"GraphQL", pages: 56, author: "some-id") {
      id
      name
      pages
      author {
        id
        firstName
        lastName
      }
    }
}
```

---

### Update book:
- Fields are optional except **id**
```
mutation {
  updateBook(id:"some-id", pages: 106) {
    id
    name
    pages
    author {
      id
      firstName
      lastName
    }
  }
}
```

---

### Delete book:
```
mutation {
  deleteBook(id: "some-id")
}
```

___
