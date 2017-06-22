<%@ Page Title="" Language="C#" MasterPageFile="~/Website.Master" AutoEventWireup="true" CodeBehind="Recipes.aspx.cs" Inherits="Lab11.Webpages.Recipes" %>


<asp:Content ID="Stylesheets" ContentPlaceHolderID="MainContent" runat="server">
    <link rel="Stylesheet" href="~/Styles/mainTableStyle.css" type="text/css" />
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>



</asp:Content>


<asp:Content ID="RecipesContent" ContentPlaceHolderID="StyleContent" runat="server">
     
    <asp:Label ID="Label1" runat="server" Text="Type:"></asp:Label>
    <select id="typeSelect" name="typeSelect" style="width: 134px">
        <option>All</option>
        <option>Ciorba</option>
        <option>Principal</option>
        <option>Desert</option>
    </select>
    <asp:Button id="typeButton" runat="server" Text="Select" OnClick="typeButton_Click" />

    <br />

   <!--  <asp:Label ID="Label2" runat="server" Text="Format: "></asp:Label>
    <select id="formatSelect" name="formatSelect" style="width: 134px">
        <option>All</option>
        <option>.doc</option>
        <option>.docx</option>
        <option>.pdf</option>
        <option>.txt</option>
    </select>
    <asp:Button id="formatButton" runat="server" Text="Select" OnClick="formatButton_Click" />
-->

    <asp:DataGrid ID="DocumentGrid" runat="server" PageSize="30" DataKeyField="id"
        AutoGenerateColumns="False" CellPadding="4" ForeColor="#333333" GridLines="None" 
        OnCancelCommand="DocumentGrid_CancelCommand" OnDeleteCommand="DocumentGrid_DeleteCommand" 
        OnEditCommand="DocumentGrid_EditCommand" OnUpdateCommand="DocumentGrid_UpdateCommand">
        <Columns>
        <asp:BoundColumn HeaderText="Id" DataField="id">
        </asp:BoundColumn>
        <asp:BoundColumn HeaderText="Name" DataField="name">
        </asp:BoundColumn>
        <asp:BoundColumn HeaderText="Author" DataField="author">
        </asp:BoundColumn>
        <asp:BoundColumn HeaderText="Numbe of pages" DataField="numberOfPages" >
        </asp:BoundColumn>
        <asp:BoundColumn HeaderText="Format" DataField="format" >
        </asp:BoundColumn>
        <asp:BoundColumn HeaderText="Type" DataField="type" >
        </asp:BoundColumn>
        <asp:EditCommandColumn EditText="Edit" CancelText="Cancel" UpdateText="Update" HeaderText="Edit">
        </asp:EditCommandColumn>
        <asp:ButtonColumn CommandName="Delete" HeaderText="Delete" Text="Delete">
        </asp:ButtonColumn>
        </Columns>
        <FooterStyle BackColor="#990000" Font-Bold="True" ForeColor="White" />
        <SelectedItemStyle BackColor="#FFCC66" Font-Bold="True" ForeColor="Navy" />
        <PagerStyle BackColor="#FFCC66" ForeColor="#333333" HorizontalAlign="Center" Mode="NumericPages" />
        <AlternatingItemStyle BackColor="White" />
        <ItemStyle BackColor="#FFFBD6" ForeColor="#333333" />
        <HeaderStyle BackColor="#990000" Font-Bold="True" ForeColor="White" />
    </asp:DataGrid>
    
    <table>
        <tr>
            <!--<td>
                <asp:Label ID="lblId" runat="server" Text="ID"></asp:Label>
                <asp:TextBox ID="TextBox1" runat="server"></asp:TextBox>
            </td> -->
            <td>
                <asp:Label ID="lblAuthor" runat="server" Text="Author"></asp:Label>
                <asp:TextBox ID="TextBox7" runat="server"></asp:TextBox>
            </td>
            <td>
                <asp:Label ID="lblName" runat="server" Text="Name"></asp:Label>
                <asp:TextBox ID="TextBox2" runat="server"></asp:TextBox>
            </td>
            <td>
                <asp:Label ID="lblType" runat="server" Text="Type"></asp:Label>
                <asp:TextBox ID="TextBox3" runat="server"></asp:TextBox>
            </td>
            <td>
                <asp:Label ID="lblRecipe" runat="server" Text="Recipe"></asp:Label>
                <asp:TextBox ID="TextBox4" runat="server"></asp:TextBox> 
            </td>
           
       </tr>
    </table>

    <asp:Button id="addD" runat="server" Text="Add document" OnClick="addD_Click" />
    </select></asp:Content>
