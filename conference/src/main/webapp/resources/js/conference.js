(function( jQuery, window, undefined ) {
    jQuery.ajax({
        url: "/html/conference_list.html",
        success: function( data ) {
            $( "#conference" ).html( data );
        }
    });

    jQuery( ".datePicker" ).datepicker({dateFormat:"yy-mm-dd"});



})( jQuery, window, undefined);