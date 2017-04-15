<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
<html>
  <head>
    <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</link>
<link rel="stylesheet" href="receipes.css"></link>
  </head>
<body>
  <h2>My Receipes</h2>
  <table border="1" class="table table-bordered">
    <xsl:for-each select="receipes/receipe">
    <tr>
      <td style="float:left" class="col-md-6 "><img class="cake-image img-rounded img-responsive " src="cake.jpg"></img></td>
      <td class="col-md-6 " >
        <h3>Ingredients</h3>
        <p class="ingredients col-md-12">
          <xsl:value-of select="ingredients"/>
          <button class="btn btn-default" type="submit">Ingredients</button>

        </p>
        <h3>Preparation</h3>
        <p class="preparation col-md-12 ">
            <xsl:value-of select="preparation"/>
            <button class="btn btn-default" type="submit">Preparation</button>

        </p>
      </td>
    </tr>
    </xsl:for-each>
  </table>
</body>
</html>
</xsl:template>
</xsl:stylesheet>
