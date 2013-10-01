(function( jQuery, window, undefined ) {
    jQuery( ".datePicker" ).datepicker({dateFormat:"yy-mm-dd"});

    jQuery("form").submit(function (e) {
        e.preventDefault();
        jQuery.ajax({
            type: "post",
            url: "/conference/list",
            data: {
                start: jQuery("#filterStart").val(),
                end: jQuery("#filterEnd").val()
            },
            success: function( data ) {
                jQuery( "#conference" ).html( data );
            }
        });
    });
    jQuery("#conference .edit").on("click", function (e) {
        e.preventDefault();
        jQuery.ajax({
            type: "get",
            url: jQuery(this).attr("href"),
            success: function( data ) {
                jQuery("#conferenceForm").html(data);
                bindDatePickerEvents(jQuery("#conferenceForm"));
                jQuery( "#conferenceForm" ).dialog({
                    "title": "Update conference",
                    buttons: {
                        "Save": function() {
                            var dialog = this;
                            jQuery.ajax({
                                type: "post",
                                url: "/conference/save",
                                data: jQuery(this).find("form").serializeArray(),
                                success:function(data) {
                                    if (data.success) {
                                        jQuery( dialog ).dialog( "close" );
                                    } else {
                                        alert ("error:" + data.error);
                                    }
                                }
                            });
                        },
                        "Cancel" :function() {
                            jQuery( this ).dialog( "close" );
                        }
                    }
                });
            }
        });
    });

    function bindDatePickerEvents(domSelector) {
        domSelector.find(".datePicker").datepicker({dateFormat:"yy-mm-dd"});
    }

})( jQuery, window, undefined);