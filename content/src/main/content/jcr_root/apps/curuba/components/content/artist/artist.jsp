<%@include file="/libs/foundation/global.jsp"%>
<%@page session="false" %>

<script type="text/javascript">
    function callSpotify(id){
    var xhttp = new XMLHttpRequest(),
        ALBUMS = "Albums: <br />",
        URI = "http://localhost:4502/bin/spotify/albums?artistId="+id
        callResponse="";

      xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
			callResponse = ALBUMS+this.responseText;
         	document.getElementById("albums").innerHTML = callResponse;
        }
      };
      xhttp.open("GET", URI, true);
      xhttp.send();

    }
</script>

<table>
  <tr>
    <th>Firstname</th>
    <th>Lastname</th>
    <th>Spotify Albums</th>
  </tr>
  <tr>
    <td>${properties.name}</td>
    <td>${properties.lastName}</td>
    <td><a onClick="callSpotify('${properties.spotifyId}'); return false;" href="#">List</a></td>
  </tr>
</table>
<br />


<div id="albums">
</div>
