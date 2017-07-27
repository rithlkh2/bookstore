var app = angular.module('myApp', ['ngResource']);

app.factory('Book', ['$resource', function ($resource) {
    return $resource('http://localhost:8989/getListBooks/:bookId', {bookId: '@id'},
        {
            updateBook: {method: 'PUT'}
        }
    );
}]);

app.controller('bookController', ['$scope', 'Book', function($scope, Book) {
    var ob = this;
    ob.books=[];
    ob.book = new Book();
    /*
     Select all data
     */
    ob.fetchAllBooks = function(){
        ob.books = Book.query();
    };
    ob.fetchAllBooks;
    /*
     add Book
     */
    ob.addBooks = function(){
        console.log('Inside save');
        if($scope.BookForm.$valid) {
            ob.book.$save(function(book){
                    console.log(book);
                    ob.flag= 'created';
                    ob.reset();
                    ob.fetchAllBooks;
                },
                function(err){
                    console.log(err.status);
                    ob.flag='failed';
                }
            );
        }
    };
    /*
     Edit Book
     */
    ob.editBook = function(id){
        console.log('Inside edit');
        ob.book = Book.get({ bookId: id}, function() {
            ob.flag = 'edit';
        });
    };
    /*
     Update Book
     */
    ob.updateBookDetail = function(){
        console.log('Inside update');
        if($scope.BookForm.$valid) {
            ob.book.$updateBook(function(book){
                console.log(book);
                ob.updatedId = book.id;
                ob.reset();
                ob.flag = 'updated';
                ob.fetchAllBooks;
            });
        }
    };
    /*
     Delete Book
     */
    ob.deleteBook = function(id){
        console.log('Inside delete');
        ob.book = Book.delete({ bookId: id}, function() {
            ob.reset();
            ob.flag = 'deleted';
            ob.fetchAllBooks;
        });
    };
    ob.reset = function(){
        ob.book = new Book();
        $scope.BookForm.$setPristine();
    };
    ob.cancelUpdate = function(id){
        ob.book = new Book();
        ob.flag= '';
        ob.fetchAllBooks;
    };
}]);