package book_store.views;


import book_store.dao.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AuthorView {

    private Integer id;
    private String name;

    public AuthorView mapToView(Author author) {
        AuthorView authorView = new AuthorView();
        authorView.setId(author.getId());
        authorView.setName(author.getName());
        return authorView;
    }

    public Author mapFromView(AuthorView authorView) {
        Author author = new Author();
        author.setId(authorView.getId());
        author.setName(authorView.getName());
        return author;
    }

    public List<AuthorView> mapToViewList(List<Author> authorList) {
        List<AuthorView> viewList = new ArrayList<>();
        for (Author author:authorList) {
            AuthorView authorView = new AuthorView();
            authorView.setId(author.getId());
            authorView.setName(author.getName());
            viewList.add(authorView);
        }

        return viewList;
    }


}
