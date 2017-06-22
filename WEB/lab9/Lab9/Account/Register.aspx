<%@ Page Title="" Language="C#" MasterPageFile="~/Website.Master" AutoEventWireup="true" CodeBehind="Register.aspx.cs" Inherits="Lab11.Account.Authenticate" %>


<asp:Content ID="RegisterContent" ContentPlaceHolderID="MainContent" runat="server">
                <div class="container">
                        <label><b>Full name</b></label>
                        <asp:TextBox ID="inputName" type="text" runat="server" />

                        <label><b>Username</b></label>
                        <asp:TextBox ID="inputUsername" type="text" runat="server"/>
            

                        <label><b>Password</b></label>
                        <asp:TextBox ID="inputPassword" type="text" runat="server" />
            
        
                        <asp:Button ID="singUpButton" runat="server" OnClick="SingUpButton_Click" Text="Sing up" />
                    </div>
</asp:Content>
