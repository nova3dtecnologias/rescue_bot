

$(document).ready(function() {
        $('#userName').blur(function(event) {
                var name = $('#userName').val();
                $.get('CommandServlet', {
                        userName : name
                }, function(responseText) {
                        $('#ajaxGetUserServletResponse').text(responseText);
                });
        });
        
        
});

