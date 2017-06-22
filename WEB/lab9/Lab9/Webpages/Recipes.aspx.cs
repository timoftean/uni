using MySql.Data.MySqlClient;
using System.Data;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Lab11.Webpages
{
    public partial class Recipes : System.Web.UI.Page
    {

        MySqlCommand cmd = new MySqlCommand();
        MySqlDataAdapter da;
        DataSet ds = new DataSet();
        MySqlConnection conn;
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        private void BindData(string c)
        {
            string connection = "server=127.0.0.1; database=lab9;user=username; password=password;";
            conn = new MySqlConnection(connection);
            conn.Open();

            cmd.CommandText = c;
            cmd.Connection = conn;
            MySqlDataAdapter da = new MySqlDataAdapter(cmd);
            DataSet ds = new DataSet();
            da.Fill(ds);
            cmd.ExecuteNonQuery();

            DocumentGrid.DataSource = ds;
            DocumentGrid.DataBind();

            conn.Close();
        }

        private void populateTableByType()
        {
            string type = Request.Form["typeSelect"].ToString();
            try
            {
                if (type == "All")
                    BindData("SELECT * FROM recipes");
                else
                    BindData("SELECT * FROM recipes WHERE type = '" + type + "'");
            }
            catch(Exception ex)
            {
                ScriptManager.RegisterClientScriptBlock(this, this.GetType(), "alertMessage", "alert('Cannot fetch data!')", true);

            }
        }
        /*
        private void populateTableByFormat()
        {
            string format = Request.Form["formatSelect"].ToString();
            try
            {
                MySqlCommand cmd = new MySqlCommand();
                if (format == "All")
                    BindData("SELECT * FROM document");
                else
                    BindData("SELECT * FROM recipes WHERE format = '" + format + "'");
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterClientScriptBlock(this, this.GetType(), "alertMessage", "alert('Cannot fetch data!')", true);

            }
        }*/

        protected void typeButton_Click(object sender, EventArgs e)
        {
            populateTableByType();
        }

        protected void formatButton_Click(object sender, EventArgs e)
        {
          
           // populateTableByFormat();
        }

        protected void DocumentGrid_EditCommand(object source, DataGridCommandEventArgs e)
        {
            DocumentGrid.EditItemIndex = e.Item.ItemIndex;
            BindData("SELECT * FROM recipes");
        }
        protected void DocumentGrid_CancelCommand(object source, DataGridCommandEventArgs e)
        {
            DocumentGrid.EditItemIndex = -1;
            BindData("SELECT * FROM recipes");
        }
        protected void DocumentGrid_DeleteCommand(object source, DataGridCommandEventArgs e)
        {
            string connection = "server=127.0.0.1; database=lab9;user=username; password=password;";
            conn = new MySqlConnection(connection);
            cmd.Connection = conn;
            int id = (int)DocumentGrid.DataKeys[(int)e.Item.ItemIndex];
            cmd.CommandText = "DELETE FROM recipes WHERE id ='" + id + "'";
            cmd.Connection.Open();
            cmd.ExecuteNonQuery();
            cmd.Connection.Close();
            DocumentGrid.EditItemIndex = -1;
            BindData("SELECT * FROM recipes");
        }

        protected void DocumentGrid_UpdateCommand(object source, DataGridCommandEventArgs e)
        {
            string id =((TextBox)e.Item.Cells[0].Controls[0]).Text;
            string author = ((TextBox)e.Item.Cells[1].Controls[0]).Text;
            string name = ((TextBox)e.Item.Cells[2].Controls[0]).Text;
            string type = ((TextBox)e.Item.Cells[3].Controls[0]).Text;
            string recipe = ((TextBox)e.Item.Cells[4].Controls[0]).Text;
            
            string connection = "server=127.0.0.1; database=lab9;user=username; password=password;";
            conn = new MySqlConnection(connection);
            cmd.CommandText = "UPDATE recipe SET author='" + author + "' , name= '" + name + "', type='"
                                                    + type + "', recipe='" + recipe + "' WHERE id ='" + id + "'";
            cmd.Connection = conn;
            cmd.Connection.Open();
            cmd.ExecuteNonQuery();
            cmd.Connection.Close();
            DocumentGrid.EditItemIndex = -1;
            BindData("SELECT * FROM recipe");
        }

        protected void addD_Click(object sender, EventArgs e)
        {
            string connection = "server=127.0.0.1; database=lab9;user=username; password=password;";
            conn = new MySqlConnection(connection);

            cmd = new MySqlCommand("INSERT INTO recipes(author, name, type, recipe) "
                 + "VALUES('" + TextBox7.Text + "','" + TextBox2.Text + "','" + TextBox3.Text
                 + "','" + TextBox4.Text + "')", conn);

            cmd.Connection = conn;
            cmd.Connection.Open();
            cmd.ExecuteNonQuery();
            BindData("SELECT * FROM recipes");
            conn.Close();

            resetTextBox();
        }

        private void resetTextBox()
        {
            TextBox7.Text = "";
            TextBox2.Text = "";
            TextBox3.Text = "";
            TextBox4.Text = "";
        }
    }
}