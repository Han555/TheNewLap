<%-- 
    Document   : seatPlans
    Created on : Nov 6, 2016, 12:26:00 AM
    Author     : catherinexiong
--%>


<!doctype html>

<html>
    <head>

        <title>Seats</title>

        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="lib/css/jquery.seat-charts.css">

        <style type="text/css">
            body {
                font-family: 'Lato', sans-serif;
            }
            a {
                color: #b71a4c;
            }
            .front-indicator {
                width: 145px;
                margin-left: auto; 
                margin-right: auto;
                //margin: 0 auto;
                margin-bottom: 30px;
                background-color: #000000;	
                color: #FFFFFF;
                text-align: center;
                padding: 3px;
                border-radius: 5px;
            }
            .wrapper {
                width: 100%;
                text-align: center;
            }
            .container {
                position: absolute;
                margin: 0 auto;
                //left:50%;
                text-align: center;
            }

            div.seatCharts-cell {
                //  color: #182C4E;
                height: 21px;
                width: 21px;
                line-height: 21px;
                font-size: 9px;

            }
            div.seatCharts-seat {
                color: #FFFFFF;
                cursor: pointer;	
            }
            div.seatCharts-row {
                height: 35px;
            }
            //   div.seatCharts-seat.available {
            //      background-color: #B9DEA0;

            //  }
            div.seatCharts-seat.available.first-class {
                /* 	background: url(vip.png); */
                background-color: #3a78c3;
            }
            div.seatCharts-seat.focused {
                background-color: #76B474;
            }
            div.seatCharts-seat.selected {
                background-color: #3a78c3;
            }
            div.seatCharts-seat.unavailable {
                background-color: #472B34;
            }
            div.seatCharts-container {
                // border-right: 1px dotted #adadad;
                width:auto;
                // padding: 20px;
                margin-left: auto; 
                margin-right: auto;
            }


        </style>
    </head>

    <%String sid = request.getParameter("sid");
        String pid = request.getParameter("pid");
    %>
    <body>

        <div class="wrapper">
            <div class="container">
                <div id="seat-map">
                    <div class="front-indicator">Stage</div>

                </div>

            </div>
        </div>

        <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
        <script src="lib/js/jquery.seat-charts.js"></script>

        <script>
            var sectionMap = [];
            var firstSeatLabel = 1;



            console.log(sectionMap);

            $(document).ready(function () {
                $.ajax({
                    url: "SectionSeatMapController?sid=<%=sid%>&pid=<%=pid%>",
                    async: false,
                    success: function (result) {
                        var str = "";
                        sectionMap = result;
                        console.log(sectionMap)
//                        for (var i = 0; i < result.length; i++) {
//                            console.log(result[i]);
//                            str += "<tr><td>" + (i + 1) + "</td><td>" + result[i].startDate + "</td><td>" + result[i].endDate + "</td>";
//                            str += '<td><button type="button" class="btn btn-default" onclick="deleteMaintenance(' + result[i].id + ');">Delete</button></td>';
//                            str += '<td><button type="button" class="btn btn-default" onclick="update(' + result[i].id + ');">Update</button></td>';
//                            str += '</tr>'
//                        }
//                        $('#items').html(str);
                    }
                });
                var sc = $('#seat-map').seatCharts({
                    map: sectionMap,
                    seats: {
                        n: {
                            price: 100,
                            classes: 'first-class', //your custom CSS class
                            category: 'Standard'

                        }


                    },
                    naming: {
                        top: false,
                        getLabel: function (character, row, column) {
                            return firstSeatLabel++;
                        },
                    },
                    legend: {
                        node: $('#legend'),
                        items: [
                            ['n', 'available', 'Standard']
                        ]
                    },
                    click: function () {
                        if (this.status() == 'available') {
                            //let's create a new <li> which we'll add to the cart items
                            $('<li>' + this.data().category + ' Seat # ' + this.settings.label + ': <b>$' + this.data().price + '</b> <a href="#" class="cancel-cart-item">[cancel]</a></li>')
                                    .attr('id', 'cart-item-' + this.settings.id)
                                    .data('seatId', this.settings.id);



                            return 'selected';
                        } else if (this.status() == 'selected') {


                            //seat has been vacated
                            return 'available';
                        } else if (this.status() == 'unavailable') {
                            //seat has been already booked
                            return 'unavailable';
                        } else {
                            return this.style();
                        }
                    }
                });



               

            });



        </script>


    </body>
</html>






