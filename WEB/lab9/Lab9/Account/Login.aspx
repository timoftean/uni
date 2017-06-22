<%@ Page Title="" Language="C#" MasterPageFile="~/Website.Master" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="Lab11.Account.Login" %>


<asp:Content ID="LoginContent" ContentPlaceHolderID="MainContent" runat="server">
        <div class="container">
            <label><b>Username</b></label>
            <asp:TextBox ID="inputUsername" type="text" runat="server"/>
            

            <label><b>Password</b></label>
            <asp:TextBox ID="inputPassword" TextMode="Password" runat="server" />
            
        
            <asp:Button ID="loginButton" runat="server" OnClick="loginButton_Click" Text="Login" />
        
            <a target="_blank" href="Register.aspx">Not a user? Sign up!</a>
        </div>
</asp:Content>
