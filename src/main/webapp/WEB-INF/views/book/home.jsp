<%--
  Created by IntelliJ IDEA.
  User: Rith
  Date: 6/30/2017
  Time: 4:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        .msg-val {
            color: red;
        }
        .msg-success {
            color: green;
        }
    </style>
    <!-- Angular JS library-->
    <%--<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
    <script type="text/javascript" src="https://code.angularjs.org/1.4.3/angular-route.min.js"></script>
    <script type="text/javascript" src="https://code.angularjs.org/1.4.3/angular-resource.min.js"></script>--%>
</head>
<body ng-app="myApp">
<div ng-controller="bookController as bookCtrl">
    <h1> Spring MVC 4 REST + AngularJS </h1>
    <form name="bookForm" method="POST">
        <table>
            <tr><td colspan="2">
                <div ng-if="bookCtrl.flag != 'edit'">
                    <h3> Add New User </h3>
                </div>
                <div ng-if="bookCtrl.flag == 'edit'">
                    <h3> Update Book for ID: {{ bookCtrl.book.id }} </h3>
                </div> </td>
            </tr>
            <tr>
                <td>Name: </td> <td><input type="text" name="name" ng-model="bookCtrl.book.name" required/>
                <span ng-show="bookForm.name.$error.required" class="msg-val">Name is required.</span> </td>
            </tr>
            <%--
            <tr>
                <td>Date: </td> <td><input type="text" name="joindate" ng-model="employeeCtrl.employee.joiningDate" required/>
                <span ng-show="employeeForm.join.$error.required" class="msg-val">Date is required.</span> </td>
            </tr>
            <tr>
                <td>Salary: </td> <td><input type="text" name="salary" ng-model="employeeCtrl.employee.salary" required/>
                <span ng-show="employeeForm.salary.$error.required" class="msg-val">Salary is required.</span> </td>
            </tr>
            <tr>
                <td>SSN: </td> <td><input type="text" name="ssn" ng-model="employeeCtrl.employee.ssn" required/>
                <span ng-show="employeeForm.ssn.$error.required" class="msg-val">SSN is required.</span> </td>
            </tr>
            <tr>
                <td colspan="2"> <span ng-if="employeeCtrl.flag=='created'" class="msg-success">Employee successfully added.</span>
                    <span ng-if="employeeCtrl.flag=='failed'" class="msg-val">Employee already exists.</span> </td>
            </tr>--%>
            <tr><td colspan="2">
                <div ng-if="bookCtrl.flag != 'edit'">
                    <input  type="button" ng-click="bookCtrl.addBooks()" value="Add Book"/>
                    <input type="button" ng-click="bookCtrl.reset()" value="Reset"/>
                </div>
                <div ng-if="bookCtrl.flag == 'edit'">
                    <input  type="button" ng-click="bookCtrl.updateBookDetail()" value="Update Book"/>
                    <input type="button" ng-click="bookCtrl.cancelUpdate()" value="Cancel"/>
                </div> </td>
            </tr>
            <tr>
                <td colspan="2"> <span ng-if="bookCtrl.flag=='deleted'" class="msg-success">Book successfully deleted.</span>
            </tr>
        </table>
    </form>
    <table>
        <tr><th>ID </th> <th>Name</th></tr>
        <tr ng-repeat="row in bookCtrl.books">
            <td><span ng-bind="row.id"></span></td>
            <td><span ng-bind="row.name"></span></td>
            <td>
                <input type="button" ng-click="bookCtrl.deleteBook(row.id)" value="Delete"/>
                <input type="button" ng-click="bookCtrl.editBook(row.id)" value="Edit"/>
                <span ng-if="bookCtrl.flag=='updated' && row.id==bookCtrl.updatedId" class="msg-success">Book successfully updated.</span> </td>
        </tr>
    </table>
</div>
<script src="${pageContext.request.contextPath}/web-resources/js/lib/angular.min.js"></script>
<script src="${pageContext.request.contextPath}/web-resources/js/lib/angular-resource.min.js"></script>
<script src="${pageContext.request.contextPath}/web-resources/js/jshome.js"></script>
</body>
</html>
