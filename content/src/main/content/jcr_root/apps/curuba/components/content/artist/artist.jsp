<%@include file="/libs/foundation/global.jsp"%>
<%@page session="false" %>
<script type="text/javascript">
    function callSpotify(id){
         var xhttp = new XMLHttpRequest();
          xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
             document.getElementById("albums").innerHTML = this.responseText;
            }
          };
          xhttp.open("GET", "localhost:4502/bin/spotify/albums?artistId="+id, true);
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

Albums:
<div id="albums">
</div>
