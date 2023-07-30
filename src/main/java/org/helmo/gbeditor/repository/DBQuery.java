package org.helmo.gbeditor.repository;

public class DBQuery {
    public static class SelectOne {
        public static String Author = "SELECT firstname, lastname FROM AUTHOR WHERE matricule=?";
        public static String Book = "SELECT id, title, summary, isbn, is_publish FROM BOOK WHERE isbn=?";
        public static String Page = "SELECT id, content, page_number FROM PAGE WHERE page_id=?";
        public static String Choice = "SELECT id, choice_redirect, text FROM CHOICE WHERE choice_id=?";
    }

    public static class SelectAll {
        public static String Author = "SELECT firstname, lastname FROM AUTHOR";
        public static String Book = "SELECT id, title, summary, isbn, is_publish FROM BOOK WHERE author_matricule=?";
        public static String Page = "SELECT id, content, page_number FROM PAGE WHERE book_id=? ORDER BY page_number";
        public static String Choice = "SELECT id, choice_redirect, text FROM CHOICE WHERE page_id=? ORDER BY choice_redirect";
    }

    public static class Insert {
        public static String Author = "INSERT INTO AUTHOR(firstname, lastname, matricule) VALUES (?,?,?)";
        public static String Book = "INSERT INTO BOOK(title, summary, isbn, author_matricule) VALUES (?,?,?,?)";
        public static String Page = "INSERT INTO PAGE(content, page_number, book_id) VALUES (?,?,?)";
        public static String Choice = "INSERT INTO CHOICE(choice_redirect, text, page_id) VALUES (?,?,?)";
    }

    public static class Update {
        public static String Author = "UPDATE AUTHOR SET firstname=?, lastname=?, matricule=? WHERE matricule=?";
        public static String Book = "UPDATE BOOK SET title=?, summary=?, isbn=?, is_publish=? WHERE id=?";
        public static String Page = "UPDATE PAGE SET content=?, page_number=?, book_id=? WHERE id=?";
        public static String Choice = "UPDATE CHOICE SET choice_redirect=?, text=?, page_id=? WHERE id=?";
    }

    public static class Delete {
        public static String Author =  "DELETE FROM AUTHOR WHERE id=?";
        public static String Book =  "DELETE FROM BOOK WHERE id=?";
        public static String Page =  "DELETE FROM PAGE WHERE id=?";
        public static String Choice =  "DELETE FROM CHOICE WHERE id=?";
    }
}
