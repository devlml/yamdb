<#-- @ftlvariable name="" type="de.justpegasus.views.AlbumView" -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="/assets/Album.css">
  <title>Album View</title>
</head>
<body>
  <h1>${album.title}</h1>
  <p>Artist: ${album.artist}</p>
  <ul>
  <#list album.tracks as track>
  	<li>${track.title}</li>
  </#list>
  </ul>
</body>
</html>