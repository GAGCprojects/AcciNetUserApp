Imports MySql.Data.MySqlClient
Imports System.Net.Mail
Imports System.String
Public Class Form1
    Dim cmd As New MySqlCommand
    Dim str As String = "server=localhost; uid=root; pwd=aiypwzqp; database=abi"
    Dim con As New MySqlConnection(str)
    Dim connetionString As String
    Dim connection As MySqlConnection
    Dim adapter As MySqlDataAdapter
    Dim cmdBuilder As MySqlCommandBuilder
    Dim ds As New DataSet
    Dim changes As DataSet
    Dim sql As String
    Dim i As Int32
    Shadows Sub load()
        Dim query As String = "select * from gstgoods"
        Dim adpt As New MySqlDataAdapter(query, con)
        Dim ds As New DataSet()
        adpt.Fill(ds, " ")
        DataGridView1.DataSource = ds.Tables(0)
        DataGridView2.DataSource = ds.Tables(0)
        con.Close()
    End Sub
    Sub insert()
        Dim ada As MySqlDataAdapter
        Dim dss As New DataSet
        Try
            con.Open()
            ada = New MySqlDataAdapter("insert into mail value('" & TextBox9.Text & "')", con)
            ada.Fill(dss, " ")
            'DataGridView3.DataSource = dss
            con.Close()
        Catch ex As Exception
            MessageBox.Show(ex.Message)
        Finally
            con.Dispose()
        End Try
    End Sub
    Sub bill()
        TextBox6.AppendText(System.DateTime.Now.ToString("dd/MM/yyyy"))
        TextBox6.AppendText(vbTab + vbTab + vbTab + vbTab + vbTab + "        " + System.DateTime.Now.ToString("HH:mm:ss"))
        TextBox6.AppendText(" " + vbNewLine)
        TextBox6.AppendText("                                   SHOPPING SYSTEM" + vbNewLine)
        TextBox6.AppendText("============================================================" + vbNewLine)
        TextBox6.AppendText("                                   Welcome to our Shop" + vbNewLine)
        TextBox6.AppendText("============================================================" + vbNewLine)
        TextBox6.AppendText("Bill for purchased items" + vbNewLine)
        TextBox6.AppendText("============================================================" + vbNewLine)
        TextBox6.AppendText("id" + vbTab + "item name" + vbTab + "gst%" + vbTab + "brand" + vbTab + "price" + vbTab + "quantity" + vbTab + "rate" + vbNewLine)
        TextBox6.AppendText("============================================================" + vbNewLine)
        TextBox6.AppendText(" " + vbNewLine)

    End Sub
    Sub add()
        TextBox4.AppendText(vbTab + vbTab + vbTab + "XYZ SHOP" + vbNewLine)
        TextBox4.AppendText(vbNewLine + "We are offering for following goods" + vbNewLine)
        TextBox4.AppendText("=====================================================" + vbNewLine)
        TextBox4.AppendText(vbTab + "    " + "category" + vbTab + vbTab + vbTab + "offer" + vbNewLine)
        TextBox4.AppendText("=====================================================" + vbNewLine)
        TextBox4.AppendText(vbNewLine + vbNewLine + vbNewLine + vbNewLine + vbNewLine)
        TextBox4.AppendText("=====================================================" + vbNewLine)
        TextBox4.AppendText("For other information visit this link")
        TextBox4.AppendText(vbTab + "=" + vbTab + "' www.xyz.com '" + vbNewLine)


    End Sub
    '  Sub billi()
    '     TextBox6.Text += (TextBox13.Text + vbTab + TextBox14.Text + vbTab + TextBox3.Text + vbTab + TextBox15.Text + vbTab + TextBox5.Text + vbNewLine)
    'End Sub
    ' Sub billu()
    '    TextBox6.Text += ("Item " + "'" + TextBox13.Text + "'" + " is updated to " + vbNewLine)
    '   TextBox6.Text += (TextBox13.Text + vbTab + TextBox14.Text + vbTab + TextBox3.Text + vbTab + TextBox15.Text + vbTab + TextBox5.Text + vbNewLine)
    'End Sub
    'Sub billd()
    '   TextBox6.Text += ("Item " + "'" + TextBox13.Text + "'" + " is deleted from the purchased list " + vbNewLine)
    'End Sub
    Sub billt()
        TextBox6.Text += ("Total price of the purchased item " + "=" + TextBox7.Text + vbNewLine)
        TextBox6.AppendText(" " + vbNewLine)
        TextBox6.Text += ("Amount Received " + "=" + TextBox11.Text + vbNewLine)
        TextBox6.Text += ("Balance Amount " + "=" + TextBox12.Text + vbNewLine)
        TextBox6.AppendText(" " + vbNewLine)
        TextBox6.Text += (vbTab + vbTab + "Thanks for shopping in our shop." + vbNewLine)
        TextBox6.AppendText("============================================================" + vbNewLine)
        ' TextBox6.AppendText(" " + vbNewLine)
        TextBox6.Text += ("You can sidecheck your purchased good's gst% in corresponding governmaent authorized website 'http://www.hindustantimes.com/interactives/gst-rate-complete-list/'." + vbNewLine + "And in 'http://www.deal4loans.com/loans/articles/list-of-gst-tax-rates-2017-revised-gst-tax-slab-in-india/'." + vbNewLine)
    End Sub
    Sub loadcombo()
        Dim query As String = "select * from gstgoods where category='" & ComboBox1.Text & "'"
        Dim adpt As New MySqlDataAdapter(query, con)
        Dim ds As New DataSet()
        adpt.Fill(ds, " ")
        DataGridView2.DataSource = ds.Tables(0)
        con.Close()
    End Sub
    Sub loadtemp()
        Dim sda As New MySqlDataAdapter
        Dim dbdataset As New DataTable
        Dim bsource As New BindingSource
        Try
            con.Open()
            Dim query As String
            query = "select id,item_name,gst_percentage,brand_name,price,quantity,rate from list"
            cmd = New MySqlCommand(query, con)
            sda.SelectCommand = cmd
            sda.Fill(dbdataset)
            bsource.DataSource = dbdataset
            DataGridView3.DataSource = bsource
            sda.Update(dbdataset)
            con.Close()
        Catch ex As Exception
            MessageBox.Show(ex.Message)
        Finally
            con.Dispose()

        End Try
    End Sub
    Sub combo()
        Dim rd As MySqlDataReader
        Try
            con.Open()
            Dim query As String
            query = "select DISTINCT category from gstgoods"
            cmd = New MySqlCommand(query, con)
            rd = cmd.ExecuteReader
            While rd.Read
                Dim cat = rd.GetString("category")
                ComboBox1.Items.Add(cat)
            End While
            con.Close()
        Catch ex As Exception
            MessageBox.Show(ex.Message)
        Finally
            con.Dispose()
        End Try

    End Sub
    Private Sub Button3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button3.Click
        Dim a As Integer
        a = ((Val(TextBox14.Text) * (Val(TextBox3.Text) / 100)) * Val(TextBox15.Text))
        TextBox5.Text = a + (Val(TextBox14.Text) * Val(TextBox15.Text))
        Dim adapater As MySqlDataAdapter
        Dim ds As New DataSet
        Try
            con.Open()
            adapater = New MySqlDataAdapter("insert into list(item_name,price,gst_percentage,quantity,rate) values ('" & TextBox13.Text & "','" & TextBox14.Text & "','" & TextBox3.Text & "','" & TextBox15.Text & "','" & TextBox5.Text & "')", con)
            adapater.Fill(ds, " ")
            DataGridView3.DataSource = ds
            con.Close()
            TextBox13.Text = ""
            TextBox14.Text = ""
            TextBox3.Text = ""
            TextBox15.Text = ""
            TextBox5.Text = ""
            loadtemp()
        Catch ex As Exception
            MessageBox.Show(ex.Message)
        Finally
            con.Dispose()
        End Try
    End Sub
    'Private Sub DataGridView1_CellClick(ByVal sender As Object, ByVal e As System.Windows.Forms.DataGridViewCellEventArgs) Handles DataGridView1.CellClick

    'End Sub

    Private Sub TextBox1_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TextBox1.TextChanged
        Dim adapater As MySqlDataAdapter
        Dim ds As New DataSet
        Try
            con.Open()
            adapater = New MySqlDataAdapter("select * from gstgoods where item_name like '%" & TextBox1.Text & "%'", con)
            adapater.Fill(ds)
            DataGridView1.DataSource = ds.Tables(0)
            con.Close()
        Catch ex As Exception

        End Try

    End Sub

    Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button2.Click
        Dim a As Integer
        a = ((Val(TextBox14.Text) * (Val(TextBox3.Text) / 100)) * Val(TextBox15.Text))
        TextBox5.Text = a + (Val(TextBox14.Text) * Val(TextBox15.Text))
    End Sub

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        'TextBox1.Clear()
        'TextBox14.Clear()
        'TextBox3.Clear()
        'TextBox15.Clear()
        'TextBox5.Clear()
        'TextBox13.Clear()
        'TextBox7.Clear()
        'TextBox11.Clear()
        'TextBox12.Clear()
    End Sub


    Private Sub DataGridView2_CellDoubleClick(ByVal sender As Object, ByVal e As System.Windows.Forms.DataGridViewCellEventArgs) Handles DataGridView2.CellDoubleClick

        Dim adapater As MySqlDataAdapter
        Dim ds As New DataSet
        Try
            con.Open()
            adapater = New MySqlDataAdapter("insert into list(item_name,gst_percentage) values ('" & TextBox13.Text & "','" & TextBox3.Text & "')", con)
            adapater.Fill(ds, " ")
            DataGridView3.DataSource = ds
            con.Close()
            TextBox1.Text = ""
            TextBox13.Text = ""
            TextBox3.Text = ""
            loadtemp()
        Catch ex As Exception
            MessageBox.Show(ex.Message)
        Finally
            con.Dispose()
        End Try
    End Sub
    Private Sub Form1_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        add()
        'mail()
        load()
        'loadnt()
        combo()
        connetionString = "server=localhost; uid=root; pwd=aiypwzqp; database=abi"
        connection = New MySqlConnection(connetionString)
        sql = "select id,item_name,gst_percentage,brand_name,price,quantity,rate from list"
        Try
            connection.Open()
            adapter = New MySqlDataAdapter(sql, connection)
            adapter.Fill(ds)
            connection.Close()
            DataGridView3.DataSource = ds.Tables(0)
        Catch ex As Exception
            MsgBox(ex.ToString)
        End Try
    End Sub

    Private Sub DataGridView3_CellClick(ByVal sender As Object, ByVal e As System.Windows.Forms.DataGridViewCellEventArgs) Handles DataGridView3.CellClick
        Dim row As DataGridViewRow = DataGridView3.CurrentRow
        Try
            TextBox2.Text = row.Cells(0).Value.ToString()
            TextBox13.Text = row.Cells(1).Value.ToString()
            TextBox3.Text = row.Cells(2).Value.ToString()
            TextBox17.Text = row.Cells(3).Value.ToString()
            TextBox14.Text = row.Cells(4).Value.ToString()
            TextBox15.Text = row.Cells(5).Value.ToString()
            TextBox5.Text = row.Cells(6).Value.ToString()
        Catch ex As Exception

        End Try
    End Sub
    Private Sub Button5_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button5.Click
        Dim adapater As MySqlDataAdapter
        Dim ds As New DataSet
        Try
            con.Open()
            adapater = New MySqlDataAdapter("Delete from list where item_name='" & TextBox13.Text & "' and id='" & TextBox2.Text & "'", con)
            adapater.Fill(ds, " ")
            DataGridView3.DataSource = ds
            con.Close()
            TextBox2.Text = ""
            TextBox13.Text = ""
            TextBox14.Text = ""
            TextBox3.Text = ""
            TextBox15.Text = ""
            TextBox5.Text = ""
            loadtemp()
        Catch ex As Exception
            MessageBox.Show(ex.Message)
        Finally
            con.Dispose()
        End Try
    End Sub

    Private Sub Button4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button4.Click
        Dim a As Integer
        a = ((Val(TextBox14.Text) * (Val(TextBox3.Text) / 100)) * Val(TextBox15.Text))
        TextBox5.Text = a + (Val(TextBox14.Text) * Val(TextBox15.Text))
        Dim adapater As MySqlDataAdapter
        Dim ds As New DataSet
        Try
            con.Open()
            adapater = New MySqlDataAdapter("Update list set item_name='" & TextBox13.Text & "' ,price='" & TextBox14.Text & "' ,gst_percentage='" & TextBox3.Text & "' ,quantity='" & TextBox15.Text & "' ,rate='" & TextBox5.Text & "' where item_name='" & TextBox13.Text & "' and id='" & TextBox2.Text & "'", con)
            adapater.Fill(ds, " ")
            DataGridView3.DataSource = ds
            con.Close()
            loadtemp()
            'billu()
            TextBox2.Text = ""
            TextBox13.Text = ""
            TextBox14.Text = ""
            TextBox3.Text = ""
            TextBox15.Text = ""
            TextBox5.Text = ""
        Catch ex As Exception
            MessageBox.Show(ex.Message)
        Finally
            con.Dispose()
        End Try
    End Sub

    Private Sub Button7_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button7.Click
        TextBox6.Clear()
        bill()
        Dim adapater As MySqlDataAdapter
        Dim ds As New DataSet
        Try
            con.Open()
            adapater = New MySqlDataAdapter("TRUNCATE TABLE list", con)
            adapater.Fill(ds, " ")
            DataGridView3.DataSource = ds
            con.Close()
            TextBox2.Text = ""
            TextBox13.Text = ""
            TextBox14.Text = ""
            TextBox3.Text = ""
            TextBox15.Text = ""
            TextBox5.Text = ""
            loadtemp()
        Catch ex As Exception
            MessageBox.Show(ex.Message)
        Finally
            con.Dispose()
        End Try
        TextBox7.Text = ""
        TextBox11.Text = ""
        TextBox12.Text = ""
    End Sub

    'Private Sub Button6_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button6.Click
    Sub calculate()
        Try


            Dim total As Integer = 0
            Dim r As Integer = 0
            r = DataGridView3.RowCount - 1
            For i As Integer = 0 To DataGridView3.RowCount - 1

                total += DataGridView3.Rows(i).Cells(6).Value
            Next
            'DataGridView3.Rows(r).Cells(3).Value() = "total price"
            'DataGridView3.Rows(r).Cells(4).Value() = total
            TextBox7.Text = total
            'billt()
        Catch ex As Exception
            MessageBox.Show("fill price and quantity")
        End Try

    End Sub
    Private Sub Button8_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button8.Click
        MessageBox.Show("sending")
        Dim Smtp_Server As New SmtpClient
        Dim e_mail As New MailMessage()
        Try
            Smtp_Server.UseDefaultCredentials = False
            Smtp_Server.Credentials = New Net.NetworkCredential("abineshabi82@gmail.com", "aiypwzqp9176448156")
            Smtp_Server.Port = 587
            Smtp_Server.EnableSsl = True
            Smtp_Server.Host = "smtp.gmail.com"
        Catch error_t As Exception
            MsgBox(error_t.ToString)
        End Try
        Try
            e_mail = New MailMessage()
            e_mail.From = New MailAddress("abineshabi82@gmail.com")
            e_mail.To.Add(TextBox9.Text)
            e_mail.Subject = "receipt"
            e_mail.IsBodyHtml = False
            e_mail.Body = TextBox6.Text
            Smtp_Server.Send(e_mail)
            MsgBox("Mail Sent")

        Catch error_t As Exception
            MessageBox.Show(error_t.Message.ToString, "Error?", MessageBoxButtons.OK, MessageBoxIcon.Error)
            'MessageBox.Show("internet slow")
            'MsgBox(error_t.ToString)
        End Try
        insert()
    End Sub
    Private Sub ComboBox1_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ComboBox1.SelectedIndexChanged
        TextBox10.Text = ComboBox1.Text
    End Sub

    Private Sub TextBox10_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TextBox10.TextChanged
        Dim query As String = "select * from gstgoods where category='" & ComboBox1.Text & "'"
        Dim adpt As New MySqlDataAdapter(query, con)
        Dim ds As New DataSet()
        adpt.Fill(ds, " ")
        DataGridView2.DataSource = ds.Tables(0)
        con.Close()
    End Sub

    Private Sub TextBox11_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TextBox11.TextChanged
        Try
            TextBox12.Text = TextBox11.Text - TextBox7.Text


        Catch ex As Exception
            TextBox11.Text = ""
            TextBox12.Text = ""
        End Try
    End Sub

    Private Sub textbox13_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TextBox13.TextChanged
        'Dim ada As MySqlDataAdapter
        'Dim dss As New DataSet
        'Try
        'con.Open()
        'ada = New MySqlDataAdapter("insert into list(item_name,gst_percentage) values ('" & TextBox13.Text & "','" & TextBox3.Text & "')", con)
        'ada.Fill(dss, " ")
        'DataGridView3.DataSource = dss
        'con.Close()
        'TextBox13.Text = ""
        'TextBox3.Text = ""
        'loadtemp()
        'Catch ex As Exception
        '   MessageBox.Show(ex.Message)
        'Finally
        '   con.Dispose()
        'End Try
        Dim adapater As MySqlDataAdapter
        Dim ds As New DataSet
        Try
            con.Open()
            adapater = New MySqlDataAdapter("select * from gstgoods where item_name like '%" & TextBox13.Text & "%'", con)
            adapater.Fill(ds)
            DataGridView1.DataSource = ds.Tables(0)
            con.Close()
        Catch ex As Exception

        End Try
    End Sub

    Private Sub Button3_KeyDown(ByVal sender As System.Object, ByVal e As System.Windows.Forms.KeyEventArgs) Handles Button3.KeyDown
        Select Case e.KeyCode
            Case Keys.I
                Button3.PerformClick()
        End Select
    End Sub

    Private Sub Button4_KeyDown(ByVal sender As System.Object, ByVal e As System.Windows.Forms.KeyEventArgs) Handles Button4.KeyDown
        Select Case e.KeyCode
            Case Keys.U
                Button4.PerformClick()
        End Select
    End Sub

    Private Sub Button5_KeyDown(ByVal sender As System.Object, ByVal e As System.Windows.Forms.KeyEventArgs) Handles Button5.KeyDown
        Select Case e.KeyCode
            Case Keys.D
                Button5.PerformClick()
        End Select
    End Sub

    Private Sub Button1_KeyDown(ByVal sender As System.Object, ByVal e As System.Windows.Forms.KeyEventArgs) Handles Button1.KeyDown
        Select Case e.KeyCode
            Case Keys.R
                Button1.PerformClick()
        End Select
    End Sub

    Private Sub Button2_KeyDown(ByVal sender As System.Object, ByVal e As System.Windows.Forms.KeyEventArgs) Handles Button2.KeyDown
        Select Case e.KeyCode
            Case Keys.C
                Button2.PerformClick()
        End Select
    End Sub


    'Private Sub TextBox15_EnabledChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TextBox15.EnabledChanged
    'Dim a As Integer
    '    a = ((Val(TextBox14.Text) * (Val(TextBox3.Text) / 100)) * Val(TextBox15.Text))
    '   TextBox5.Text = a + (Val(TextBox14.Text) * Val(TextBox15.Text))
    '  billi()
    'Dim adapater As MySqlDataAdapter
    'Dim ds As New DataSet
    '   Try
    '      con.Open()
    '     adapater = New MySqlDataAdapter("insert into list(item_name,price,gst_percentage,quantity,rate) values ('" & TextBox13.Text & "','" & TextBox14.Text & "','" & TextBox3.Text & "','" & TextBox15.Text & "','" & TextBox5.Text & "')", con)
    '    adapater.Fill(ds, " ")
    '   DataGridView3.DataSource = ds
    '  con.Close()
    ' TextBox13.Text = ""
    '        TextBox14.Text = ""
    '       TextBox3.Text = ""
    '      TextBox15.Text = ""
    '     TextBox5.Text = ""
    'loadtemp()
    'Catch ex As Exception
    '   MessageBox.Show(ex.Message)
    'Finally
    '      con.Dispose()
    '   End Try
    'End Sub

    Private Sub Button9_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button9.Click
        Try
            cmdBuilder = New MySqlCommandBuilder(adapter)
            changes = ds.GetChanges()
            If changes IsNot Nothing Then
                adapter.Update(changes)
            End If
            MsgBox("Changes Done")
        Catch ex As Exception
            MsgBox(ex.ToString)
        End Try
        'save()
    End Sub

    Private Sub Button10_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button10.Click

    End Sub

    'Private Sub TextBox15_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TextBox15.TextChanged
    'Dim a As Integer
    '   a = ((Val(TextBox14.Text) * (Val(TextBox3.Text) / 100)) * Val(TextBox15.Text))
    '  TextBox5.Text = a + (Val(TextBox14.Text) * Val(TextBox15.Text))
    'Dim adapater As MySqlDataAdapter
    'Dim ds As New DataSet
    '   Try
    '      con.Open()
    '     adapater = New MySqlDataAdapter("Update list set item_name='" & TextBox13.Text & "' ,price='" & TextBox14.Text & "' ,gst_percentage='" & TextBox3.Text & "' ,quantity='" & TextBox15.Text & "' ,rate='" & TextBox5.Text & "' where item_name='" & TextBox13.Text & "'", con)
    '    adapater.Fill(ds, " ")
    '   DataGridView3.DataSource = ds
    '  con.Close()
    ' loadtemp()
    '        billu()
    '       TextBox13.Text = ""
    '      TextBox14.Text = ""
    '     TextBox3.Text = ""
    '    TextBox15.Text = ""
    '   TextBox5.Text = ""
    '     Catch ex As Exception
    '        MessageBox.Show(ex.Message)
    '   Finally
    '      con.Dispose()
    ' End Try
    'End Sub


    ' Private Sub DataGridView3_CellEndEdit(ByVal sender As System.Object, ByVal e As System.Windows.Forms.DataGridViewCellEventArgs) Handles DataGridView3.CellEndEdit
    'Dim row As DataGridViewRow = DataGridView3.CurrentRow
    '   Try
    '      TextBox13.Text = row.Cells(0).Value.ToString()
    ' Catch ex As Exception

    '    End Try
    'End Sub



    Private Sub DataGridView3_CellValueChanged(ByVal sender As System.Object, ByVal e As System.Windows.Forms.DataGridViewCellEventArgs) Handles DataGridView3.CellValueChanged
        Dim row As DataGridViewRow = DataGridView3.CurrentRow
        Try
            TextBox2.Text = row.Cells(0).Value.ToString()
            TextBox13.Text = row.Cells(1).Value.ToString()
            TextBox3.Text = row.Cells(2).Value.ToString()
            TextBox17.Text = row.Cells(3).Value.ToString()
            TextBox14.Text = row.Cells(4).Value.ToString()
            TextBox15.Text = row.Cells(5).Value.ToString()
            TextBox5.Text = row.Cells(6).Value.ToString()
        Catch ex As Exception

        End Try
        Dim a As Integer
        a = ((Val(TextBox14.Text) * (Val(TextBox3.Text) / 100)) * Val(TextBox15.Text))
        TextBox5.Text = a + (Val(TextBox14.Text) * Val(TextBox15.Text))
        Dim adapater As MySqlDataAdapter
        Dim ds As New DataSet
        Try
            con.Open()
            adapater = New MySqlDataAdapter("Update list set item_name='" & TextBox13.Text & "' ,brand_name='" & TextBox17.Text & "' ,price='" & TextBox14.Text & "' ,gst_percentage='" & TextBox3.Text & "' ,quantity='" & TextBox15.Text & "' ,rate='" & TextBox5.Text & "' where item_name='" & TextBox13.Text & "' and id='" & TextBox2.Text & "'", con)
            adapater.Fill(ds, " ")
            DataGridView3.DataSource = ds
            con.Close()
            loadtemp()
            'billu()
            TextBox2.Text = ""
            TextBox13.Text = ""
            TextBox14.Text = ""
            TextBox17.Text = ""
            TextBox3.Text = ""
            TextBox15.Text = ""
            TextBox5.Text = ""
        Catch ex As Exception
            MessageBox.Show(ex.Message)
        Finally
            con.Dispose()
        End Try
    End Sub
    Private Sub TextBox6_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TextBox6.TextChanged
        ' MessageBox.Show("not editable")
    End Sub

    Private Sub Button11_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button11.Click
        calculate()
        TextBox6.Text = ""
        bill()
        Dim le As New Integer
        'Dim str As String
        For i As Integer = 0 To DataGridView3.RowCount - 1
            For j As Integer = 0 To 6
                'TextBox6.Text &= (DataGridView3.Rows(i).Cells(j).Value) & vbTab
                If (j = 1) Then
                    TextBox16.Text = (DataGridView3.Rows(i).Cells(j).Value)
                    le = TextBox16.Text.Length
                    If (le > 10) Then
                        Dim str As String
                        Dim strArr() As String
                        Dim count As Integer
                        str = TextBox16.Text
                        strArr = str.Split(" ")
                        For count = 0 To strArr.Length - 1
                            TextBox6.Text &= strArr(count) '& vbNewLine & vbTab
                            If (count = strArr.Length - 1) Then
                                TextBox6.Text &= ""
                            Else
                                TextBox6.Text &= vbNewLine & vbTab
                            End If
                        Next
                        j = j + 1
                        TextBox6.Text &= vbTab & vbTab
                        'TextBox6.Text &= vbNewLine
                        'TextBox6.Text &= "  " & vbTab
                        'TextBox6.Text &= (DataGridView3.Rows(i).Cells(j).Value)
                    ElseIf (le <= 9) Then  '8
                        TextBox6.Text &= (DataGridView3.Rows(i).Cells(j).Value) & vbTab & vbTab
                        j = j + 1
                    End If
                End If
                TextBox6.Text &= (DataGridView3.Rows(i).Cells(j).Value) & vbTab
            Next
            TextBox6.Text &= vbNewLine
        Next
        TextBox6.AppendText("============================================================" + vbNewLine)
        billt()
    End Sub


    Private Sub DataGridView1_MouseClick(ByVal sender As System.Object, ByVal e As System.Windows.Forms.MouseEventArgs) Handles DataGridView1.MouseClick
        Dim row As DataGridViewRow = DataGridView1.CurrentRow
        Try
            TextBox1.Text = row.Cells(0).Value.ToString()
            TextBox3.Text = row.Cells(1).Value.ToString()
            TextBox13.Text = row.Cells(0).Value.ToString()
        Catch ex As Exception

        End Try

    End Sub

    Private Sub DataGridView1_CellDoubleClick(ByVal sender As System.Object, ByVal e As System.Windows.Forms.DataGridViewCellEventArgs) Handles DataGridView1.CellDoubleClick
        Dim adapater As MySqlDataAdapter
        Dim ds As New DataSet
        Try
            con.Open()
            adapater = New MySqlDataAdapter("insert into list(item_name,gst_percentage) values ('" & TextBox13.Text & "','" & TextBox3.Text & "')", con)
            adapater.Fill(ds, " ")
            DataGridView3.DataSource = ds
            con.Close()
            TextBox1.Text = ""
            TextBox13.Text = ""
            TextBox3.Text = ""
            loadtemp()
        Catch ex As Exception
            MessageBox.Show(ex.Message)
        Finally
            con.Dispose()
        End Try
    End Sub

    '   Private Sub TextBox6_MouseEnter(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TextBox6.MouseEnter
    '      MessageBox.Show("not editable")
    '   End Sub

    Private Sub DataGridView2_MouseClick(ByVal sender As System.Object, ByVal e As System.Windows.Forms.MouseEventArgs) Handles DataGridView2.MouseClick
        Dim row As DataGridViewRow = DataGridView2.CurrentRow
        Try
            TextBox1.Text = row.Cells(0).Value.ToString()
            TextBox3.Text = row.Cells(1).Value.ToString()
            TextBox13.Text = row.Cells(0).Value.ToString()
        Catch ex As Exception

        End Try
    End Sub

    Private Sub Button12_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button12.Click
        Label3.Text = ("reaching customers")
        Dim query As String = "select * from mail"
        Dim adpt As New MySqlDataAdapter(query, con)
        Dim ds As New DataSet()
        adpt.Fill(ds, " ")
        DataGridView4.DataSource = ds.Tables(0)
        con.Close()
        TextBox8.Text = ""
        For i As Integer = 0 To DataGridView4.RowCount - 1
            For j As Integer = 0 To 0
                TextBox8.Text &= (DataGridView4.Rows(i).Cells(j).Value)
            Next
            TextBox8.Text &= ","
        Next
        Dim ml As New MailMessage()

        ml.From = New MailAddress("abineshabi82@gmail.com")
        ml.Subject = "Advertisement"
        ml.Body = TextBox4.Text
        Dim SMTPServer As New SmtpClient("smtp.gmail.com")
        SMTPServer.Port = 587
        SMTPServer.Credentials = New System.Net.NetworkCredential("abineshabi82@gmail.com", "aiypwzqp9176448156")
        SMTPServer.EnableSsl = "smtp.gmail.com" <> "smtp.mail.yahoo.com"
        Dim str As String
        Dim strArr() As String
        Dim count As Integer
        str = TextBox8.Text
        strArr = str.Split(",")
        Label3.Text = "Broadcasting..."
        For count = 0 To strArr.Length - 1
            Try
                ml.To.Add(strArr(count))
            Catch ex As Exception
                Label3.Text = "Broadcasting..."
                MessageBox.Show("Broadcasting")
            End Try

            Try
                SMTPServer.Send(ml)
                Label3.Text = "Broadcasted"
            Catch ex As SmtpException
                Label3.Text = "Network slow or other interactions"
                MessageBox.Show("Network slow or other interactions")
            End Try

        Next
    End Sub


    '  Private Sub Button13_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button13.Click

    '    Dim openFileDialog1 As New OpenFileDialog()

    '    openFileDialog1.InitialDirectory = "c:\"
    '   openFileDialog1.Filter = "txt files (*.txt)|*.txt|All files (*.*)|*.*"
    '  openFileDialog1.FilterIndex = 2
    ' openFileDialog1.RestoreDirectory = True

    '    If openFileDialog1.ShowDialog() = System.Windows.Forms.DialogResult.OK Then

    '       TextBox16.Text = openFileDialog1.FileName
    '  End If
    '(        Dim OpenFileDialog1 As New OpenFileDialog()
    '       OpenFileDialog1.ShowDialog()
    '      Dim Paths As New System.Collections.Specialized.StringCollection
    '     Paths.AddRange(OpenFileDialog1.FileNames)
    '    Clipboard.SetFileDropList(Paths)
    '   TextBox16.Paste()           )
    ' End Sub

    Private Sub TextBox9_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TextBox9.TextChanged
        DataGridView4.Visible = True
        Dim adapater As MySqlDataAdapter
        Dim ds As New DataSet
        Try
            con.Open()
            adapater = New MySqlDataAdapter("select * from mail where ID like '%" & TextBox9.Text & "%'", con)
            adapater.Fill(ds)
            DataGridView4.DataSource = ds.Tables(0)
            con.Close()
        Catch ex As Exception

        End Try
    End Sub

    Private Sub DataGridView4_CellClick(ByVal sender As System.Object, ByVal e As System.Windows.Forms.DataGridViewCellEventArgs) Handles DataGridView4.CellClick
        Dim row As DataGridViewRow = DataGridView4.CurrentRow
        Try
            TextBox9.Text = row.Cells(0).Value.ToString()
        Catch ex As Exception

        End Try
        DataGridView4.Visible = False
    End Sub

    Private Sub TextBox7_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TextBox7.TextChanged
        Try
            TextBox12.Text = TextBox11.Text - TextBox7.Text


        Catch ex As Exception
            TextBox11.Text = ""
            TextBox12.Text = ""
        End Try
    End Sub

    Private Sub Button6_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button6.Click
        calculate()
    End Sub

    Private Sub TextBox6_MouseEnter(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TextBox6.MouseEnter
        MessageBox.Show("not editable")
    End Sub
End Class



