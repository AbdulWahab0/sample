/**
 * Component
 */


// Static variables
var DELETE_REST_URL = "/api/v1/application/";

/*
 * Controllers
 */

var application = new Object();

application.list = function list(ctx) {
    logger.i("Current page set to: " + ctx.pathname);

    iaasTimer.started ? iaasTimer.stop() : '';
    clearInterval(alchemyInterval);

    $.post("/application", function (data) {
        $("#content").html(data);
        $("#menu-applications").addClass("active");
    });
};

application.add = function add(ctx) {
    logger.i("Current page set to: " + ctx.pathname);

    iaasTimer.started ? iaasTimer.stop() : '';
    clearInterval(alchemyInterval);

    $.post("/application/add", function (data) {
        console.log("fdsafdsafdsa");
        $("#content").html(data);
        $("#menu-applications").addClass("active");

    });
};

application.configure = function add(ctx) {
    logger.i("Current page set to: " + ctx.pathname);

    iaasTimer.started ? iaasTimer.stop() : '';
    clearInterval(alchemyInterval);

    var id = ctx.params.id;
    $.post("/application/" + id + "/configure", function (data) {
        $("#content").html(data);
        $("#menu-applications").addClass("active");
    });
};

application.automatic = function automatic(ctx) {
    logger.i("Current page set to: " + ctx.pathname);

    iaasTimer.started ? iaasTimer.stop() : '';
    clearInterval(alchemyInterval);

    var id = ctx.params.id;
    $.post("/application/" + id + "/automatic", function (data) {
        $("#content").html(data);
        $("#menu-applications").addClass("active");
    });
};

/*const ALCHEMY_GLOBAL = alchemy; // hack for re-initialise alchemy
var alchemyInterval = -1;*/
application.visual = function add(ctx) {
    logger.i("Current page set to: " + ctx.pathname);

    iaasTimer.started ? iaasTimer.stop() : '';

    var gsgid = ctx.params.gsgid;

    $.post("/application/" + gsgid + "/visual", function (data) {
        $("#content").html(data);
        $("#menu-applications").addClass("active");

        (function deploymentGraphHandler() {

            $.ajax({
                type: 'POST',
                url: "/api/v1/groundedservicegraph/status/" + $("section#main.deployment-process #gsgid").val()
            }).success(function (data, status, xhr) {
                if ("SUCCESS" === data.code) {
                    if (!$.isEmptyObject(data.returnobject)) {
                        initAlchemy(data.returnobject);
                    }
                } else {
                    console.log(data.message);
                }
            }).error(function(jqXHR, textStatus, errorThrown) {
                 console.log(jqXHR.status + " (" + errorThrown + ").");
            });

        })();

        function initAlchemy(graphData) {

            destroyAlchemy();

            alchemy.begin({

                divSelector: '#deploy-process',

                // ---- Graph ----//
                graphWidth: function(){ return $('#main .wrapper').width(); },
                graphHeight: function(){ return 600; },
                linkDistancefn: function(){ return 340; },
                alpha: 1,
                forceLocked: true,
                initialScale: 0.7,
                initialTranslate: [90, 50],
                //rootNodeRadius: 135,

                // ---- Load Data ---- //
                dataSource: graphData,

                // ---- Source ---- //
                backgroundColour: "white",
                nodeCaption: function(node) {
                    return node.name;
                },
                nodeStyle: {
                    "all": {
                        "radius": 30,
                        "borderColor": "#eee",
                        "borderWidth": function(d, radius) {
                            return radius / 5;
                        }
                    }
                },
                nodeClick: function(node) {
                    console.log(node);
                    $("#component-name span").text(node._properties.name + " ( " + node._properties.componentId + " )");
                    $("#component-iaas span").text(node._properties.iaasName);

                    $("#component-private-ipaddress span").text(node._properties.privateIp);

                    $("#component-public-ipaddress span a").text(node._properties.publicIp);
                    $("#component-public-ipaddress span a").attr("href", "http://" + node._properties.publicIp);
                },

                nodeTypes: {
                    status: ["loading", "bootstrapped", "initialized", "deployed", "blocked", "started", "stopped", "undeployed", "erroneous"]
                },

                // ----- Edges ----- //
                edgeCaption: function(edge) {
                    return edge.relation;
                },
                /*edgeClick: function(edge) {
                    logger.o(edge);
                },*/
                edgeStyle: {
                    "all": {
                        "width": 4,
                        "color": "#0277BD",
                        "stroke-width": 10,
                        "opacity": 0.3,
                        "directed": true,
                        "curved": true,
                        "selected": {
                            "opacity": 1,
                        },
                        "highlighted": {
                            "opacity": 1
                        },
                        "hidden": {
                            "opacity": 0
                        }
                    }
                },
                edgeTypes: {
                    status: ["pending", "resolved"]
                },
                curvedEdges: false,
                //edgeOverlayWidth: 140,
                directedEdges: true

            });

            if (graphData.isMonitorable) {
                $(".deployment-process .wrapper i.fa-spinner").addClass("remove");
                return;
            }

            // Start real time check for graph status
            alchemyInterval = setInterval(function() {
                $.ajax({
                    type: 'POST',
                    url: "/api/v1/groundedservicegraph/status/" + $("section#main.deployment-process #gsgid").val()
                }).success(function (data, status, xhr) {
                    if ("SUCCESS" === data.code) {
                        if (!$.isEmptyObject(data.returnobject)) {

                            $.each(data.returnobject.nodes, function(k, v) {
                                $("#node-" + v.id).removeClass("loading bootstrapped initialized deployed blocked started stopped undeployed erroneous").addClass(v.status);
                            });
                            $.each(data.returnobject.edges, function(k, v) {
                                $("#edge-" + v.source + "-" + v.target + "-0").removeClass("pending resolved").addClass(v.status);
                            });

                            if (data.returnobject.isMonitorable) {
                                 destroyAlchemy();
                                 $(".deployment-process .wrapper i.fa-spinner").addClass("remove");
                            }
                        }
                    } else {
                        console.log(data.message);
                        $(".deployment-process .wrapper i.fa-spinner").addClass("remove");
                        destroyAlchemy();
                    }
                }).error(function(jqXHR, textStatus, errorThrown) {
                     console.log(jqXHR.status + " (" + errorThrown + ")");
                     $(".deployment-process .wrapper i.fa-spinner").addClass("remove");
                     destroyAlchemy();
                });
            }, 3000);
        };//EoFunction

        function destroyAlchemy() {
            clearInterval(alchemyInterval);
            alchemyInterval = -1;
            alchemy = ALCHEMY_GLOBAL;
        };

    });
};

application.info = function add(ctx) {
    logger.i("Current page set to: " + ctx.pathname);

    iaasTimer.started ? iaasTimer.stop() : '';
    clearInterval(alchemyInterval);

    var gsgid = ctx.params.gsgid;

    $.post("/application/" + gsgid + "/info", function (data) {
        $("#content").html(data);
        $("#menu-applications").addClass("active");
    });
};

application.statistics = function add(ctx) {
    logger.i("Current page set to: " + ctx.pathname);

    iaasTimer.started ? iaasTimer.stop() : '';
    clearInterval(alchemyInterval);

    var gsgid = ctx.params.gsgid;
    $.post("/application/" + gsgid + "/statistics", function (data) {
        $("#content").html(data);
        $("#menu-applications").addClass("active");
    });
};


var cy;
var nodes = [];
var edges = [];
var comList = [];
var edgeObj;
var nodeObj;
var json;
var jsonComList;
var snapshot;

function addNewSG(ctx) {
    logger.d("addNewSG()");
    $("#loading").remove();
    logger.i("Current page set to ADD new SG: " + ctx.pathname);

    iaasTimer.started ? iaasTimer.stop() : '';

    json = {
        "returnobject": {
            "id": "2d670d3a296185a41568c5ab"
            , "userName": "arcadia"
            , "nodeCount": null
            , "isPublic": true
            , "serviceGraph": {
                "descriptiveSGMetadata": {
                    "sgid": ""
                    , "tags": null
                    , "sgName": "NewServiceGraph"
                    , "sgDescription": ""
                }
                , "graphNodeDescriptor": {
                    "graphNode": []
                }
                , "runtimePolicyDescriptor": {
                    "runtimePolicy": []
                }
            }
            , "serviceGraphJson": ""
            , "componentList": []
        }
    }
    cy = null;
    nodes = [];
    edges = [];
    comList = [];
    edgeObj = null;
    nodeObj = null;
    jsonComList = null;
    snapshot = null;
    initGraph("#cyGRAPH");
}

application.edit = function edit(ctx) {
    var id = ctx.params.id;
    logger.i("Current page set to: " + ctx.pathname);

    iaasTimer.started ? iaasTimer.stop() : '';
    clearInterval(alchemyInterval);

    if (ctx.pathname == "/application/add") {
        $.post("/application/" + id, function (data) {
            $("#content").html(data);
            $("#menu-applications").addClass("active");
            addNewSG(ctx);
        });
    } else {
        id = ctx.params.id;
        $.post("/application/" + id, function (data) {
            $("#content").html(data);
            $("#menu-applications").addClass("active");
            url = "/api/v1/servicegraph/getSGID/" + id;
            $.ajax({
                url: url
                , }).success(function (data, status, xhr) {
                if ("SUCCESS" === data.code) {
                    $("#loading").remove();
                    json = data;
                    snapshot = Defiant.getSnapshot(json);
                    $.each(json.returnobject.serviceGraph.graphNodeDescriptor.graphNode, function (i, obj) {
                        var Oobj = JSON.search(snapshot, '//componentList/*[cnid=\'' + obj.cnid + '\']');
                        var nnname = Oobj[0].cnname;
                        nodeObj = {
                            name: nnname
                            , nid: obj.nid
                            , cnid: obj.cnid
                        };
                        nodes.push(nodeObj);
                        $.each(obj.graphDependency, function (j, depObj) {
                            edgeObj = {
                                source: obj.nid
                                , target: depObj.nid
                                , type: null
                            };
                            edges.push(edgeObj);
                        });
                    });
                    try {
                        initGraph("#cyGRAPH");
                    } catch (err) {
                        $("#loading").remove();
                        arcadia.notify("Error in rendering service graph : " + err, {
                            mode: "notification-error"
                        });
                    }
                    addNodesnEdges();
                }
                $("#info").hide();
            });
        });
    }
};
application.undeployed = function undeployed(ctx) {
    logger.i("Current page set to: " + ctx.pathname);

    iaasTimer.started ? iaasTimer.stop() : '';
    clearInterval(alchemyInterval);

    $.post("/application/undeployed", function (data) {
        $("#content").html(data);
        $("#menu-applications").addClass("active");
    });
};
/*
 *  Hanlders
 */
function editApplicationHandler() {
    //Create application object
    var application = new Object();
    //Make the add call
    $.ajax({
        type: 'PUT'
        , data: JSON.stringify(user)
        , url: REGISTER_REST_URL
        , contentType: "application/json; charset=utf-8"
    }).success(function (data, status, xhr) {
        //Check if the new account is created
        if ("SUCCESS" === data.code) {
            logger.i("Registration is success");
            //Redirect user to login
            arcadia.notify(data.message);
            page.redirect("/application");
        } else {
            arcadia.notify(data.message, {
                mode: "notification-error"
            });
        }
    });
}

function manuallyDeployApplicationHandler(applicationid, dataModel) {
    console.log(formSerializer(dataModel));

    var parameters = $('#configure').serializeJSON();
    var data = JSON.parse(formSerializer(dataModel));

    //data["parameters"] = parameters["parameters"];

    data["nodePlacementPlans"].forEach(function (e) {
        e["parameters"] = parameters["parameters"][e["nid"]];
    });

    //Make the add call
    $.post({
        data: JSON.stringify(data)
        , url: "/api/v1/groundedservicegraph/ground"
        , contentType: "application/json; charset=utf-8"
    }).success(function (data, status, xhr) {
        //Check if the new account is created
        if ("SUCCESS" === data.code) {
            logger.i("Application is being deployed");
            //Redirect user to login
            arcadia.notify(data.message);
            page.redirect("/application");
        } else {
            arcadia.notify(data.message, {
                mode: "notification-error"
            });
        }
    });
}

function editUserHandler() {
    //Create User object
    var user = new Object();
    user.id = $("#uid").val();
    user.username = $("#username").val();
    user.password = $("#password").val();
    user.firstname = $("#firstname").val();
    user.lastname = $("#lastname").val();
    user.email = $("#email").val();
    //Make the add call
    $.ajax({
        type: 'PUT'
        , data: JSON.stringify(user)
        , url: REGISTER_REST_URL
        , contentType: "application/json; charset=utf-8"
    }).success(function (data, status, xhr) {
        //Check if the new account is created
        if ("SUCCESS" === data.code) {
            logger.i("Registration is success");
            //Redirect user to login
            arcadia.notify(data.message);
            page.redirect("/user");
        } else {
            arcadia.notify(data.message, {
                mode: "notification-error"
            });
        }
    });
}

function deleteApplicationHandler(applicationid) {
    logger.d("Trying to delete application with id: " + applicationid);
    $.confirm({
        text: "Are you sure you want to delete that Service Graph as well as all the depending policies and rules?"
        , title: "Confirmation required"
        , confirm: function (button) {
            $.ajax({
                type: 'DELETE'
                , url: "/api/v1/servicegraph/" + applicationid
                , }).success(function (data, status, xhr) {
                arcadia.notify("Service Graph is succesfully deleted");
                page.redirect("/application/template");
            }).fail(function (error) {
                arcadia.notify("Error in deleting service graph", {
                    mode: "notification-error"
                });
                logger.e("Code: " + error.status + " Message: " + error.responseText);
            });
        }
        , cancel: function (button) {
            // nothing to do
        }
        , confirmButton: "Yes"
        , cancelButton: "No"
        , post: true
        , confirmButtonClass: "btn-danger"
        , cancelButtonClass: "btn-default"
        , dialogClass: "modal-dialog"
    });

    page.redirect("/application/template");
}

function undeployApplicationHandler(applicationid) {
    logger.d("Trying to undeploy application with id: " + applicationid);
    $.confirm({
        text: "Are you sure you want to undeploy that Service Graph as well as all the depending policies and rules?"
        , title: "Confirmation required"
        , confirm: function (button) {
            $.ajax({
                type: 'POST'
                , url: "/api/v1/groundedservicegraph/unground?gsgid=" + applicationid
                , contentType: "application/json; charset=utf-8"
                , }).success(function (data, status, xhr) {
                arcadia.notify("Service Graph has beed undeployed succesfully");
                page.redirect("/application");
            }).fail(function (error) {
                arcadia.notify("Error in undeploying service graph", {
                    mode: "notification-error"
                });
                logger.e("Code: " + error.status + " Message: " + error.responseText);
            });
        }
        , cancel: function (button) {
            // nothing to do
        }
        , confirmButton: "Yes"
        , cancelButton: "No"
        , post: true
        , confirmButtonClass: "btn-danger"
        , cancelButtonClass: "btn-default"
        , dialogClass: "modal-dialog"
    });
}

function viewApplicationMetric() {
    var metric = new Object();
    metric.gsgid = $("#gsgid").val();
    metric.metricid = $("#metricid").val();

    console.log(metric);

    $.ajax({
        type: 'GET'
        , url: "/api/v1/nodemeasurement/gsgid/" + metric.gsgid + metric.metricid
        , contentType: "application/json; charset=utf-8"
    }).success(function (data, status, xhr) {
        //Check if the new account is created
        if ("SUCCESS" === data.code) {

            if (data.returnobject.length === 0) {
                arcadia.notify("No data available", {mode: "notification-error"});
                return;
            }

            logger.i("Loaded node metrics");
            arcadia.notify(data.message);

            $("#metric-title").text(metric.metricid);

            var metricsdata = new Array();

            for (var i in data.returnobject) {
                var metricsobject = new Object();
                metricsobject.date = data.returnobject[i].metricdate;
                metricsobject.total = data.returnobject[i].metricValue;
                metricsdata.push(metricsobject);
            }

            d3.select("svg").remove();

            /*
             * GRAPH
             */

            var data = metricsdata;
            var formatDate = d3.time.format("%Y-%m-%d %H:%M:%S").parse;

            var margin = {top: 40, right: 0, bottom: 40, left: 40},
            width = 500,
                    height = 400;

            var x = d3.time.scale()
                    .domain([formatDate(data[0].date), formatDate(data[data.length - 1].date), 1])
                    .rangeRound([0, width - margin.left - margin.right]);

            var y = d3.scale.linear()
                    .domain([0, d3.max(data, function (d) {
                            return d.total;
                        })])
                    .range([height - margin.top - margin.bottom, 0]);

            var xAxis = d3.svg.axis()
                    .scale(x)
                    .orient('bottom')
                    .ticks(d3.time.days, 1)
                    //.tickFormat(d3.time.format('%a %d'))
                    .tickFormat(d3.time.format("%A %d %B"))
                    .tickSize(0)
                    .tickPadding(8);

            var yAxis = d3.svg.axis()
                    .scale(y)
                    .orient('left')
                    .tickPadding(8);

            var svg = d3.select('#metric').append('svg')
                    .attr('class', 'chart')
                    .attr('width', width)
                    .attr('height', height)
                    .append('g')
                    .attr('transform', 'translate(' + margin.left + ', ' + margin.top + ')');

            svg.selectAll('.chart')
                    .data(data)
                    .enter().append('rect')
                    .attr('class', 'bar')
                    .attr('x', function (d) {
                        return x(new Date(d.date));
                    })
                    .attr('y', function (d) {
                        return height - margin.top - margin.bottom - (height - margin.top - margin.bottom - y(d.total))
                    })
                    .attr('width', 10)
                    .attr('height', function (d) {
                        return height - margin.top - margin.bottom - y(d.total)
                    });

            svg.append('g')
                    .attr('class', 'x axis')
                    .attr('transform', 'translate(0, ' + (height - margin.top - margin.bottom) + ')')
                    .call(xAxis)
                    .selectAll("text")
                    .style("text-anchor", "end")
                    .attr("dx", "-.8em")
                    .attr("dy", ".15em")
                    .attr("transform", "rotate(-90)");

            svg.append('g')
                    .attr('class', 'y axis')
                    .call(yAxis);

        } else {
            arcadia.notify(data.message, {
                mode: "notification-error"
            });
        }
    });
}

function getComList() {
    url = "/api/v1/component/getPublicOrPrivatelyOwnedComponents";
    $.ajax({
        url: url
        , }).success(function (data, status, xhr) {
        if ("SUCCESS" === data.code) {
            jsonComList = data;
            $.each(jsonComList.returnobject, function (i, obj) {
                comList.push(obj);
            });
            updateCompListMenu();
        }
    });
}

function addNodesnEdges() {
    for (id in nodes) {
        cy.add({
            group: "nodes"
            , data: {
                id: nodes[id].nid
                , cnid: nodes[id].cnid
                , type: "type1"
                , name: nodes[id].name +"("+nodes[id].nid+")"
            }
            , });
    }
    for (edgeNode in edges) {
        if (edges[edgeNode].source && edges[edgeNode].target) {
            cy.add({
                group: "edges"
                , data: {
                    id: "e" + edgeNode
                    , source: edges[edgeNode].source
                    , target: edges[edgeNode].target
                }
                , });
        }
    }
    if (nodes.length > 2) {
        var layout = cy.makeLayout({
            name: 'breadthfirst'
        });
        layout.run();
        refresh2();
    } else {
        var layout = cy.makeLayout({
            name: 'circle'
        });
        layout.run();
        refresh2();
    }
}

function initGraph(div) {
    logger.d("initGraph()");
    //INITIALIZATION
    //element = document.getElementById(div);
    var domContainer = $(div);
    //var domContainer = document.getElementById("cyEDIT");
    cy = cytoscape({
        container: domContainer
        , style: [{
                selector: 'node[type="type1"]'
                , style: {
                    'content': 'data(name)'
                    , 'text-opacity': 0.5
                    , 'text-valign': 'center'
                    , 'text-halign': 'right'
                    , 'background-color': '#eb5424'
                    , }
            }, {
                selector: 'node[type="type2"]'
                , style: {
                    'content': 'data(name)'
                    , 'text-opacity': 0.5
                    , 'text-valign': 'center'
                    , 'text-halign': 'right'
                    , 'background-color': '#2CE8F2'
                    , }
            }, {
                selector: 'node[type="default"]'
                , style: {
                    'content': 'data(name)'
                    , 'text-opacity': 0.5
                    , 'text-valign': 'center'
                    , 'text-halign': 'right'
                    , 'background-color': '#ff3399'
                    , }
            }, {
                selector: 'edge'
                , style: {
                    'width': 3
                    , 'line-color': '#080808'
                    , 'target-arrow-color': '#080808'
                    , 'target-arrow-shape': 'triangle'
                }
            }, {
                selector: 'edge[type="default"]'
                , style: {
                    'line-color': '#080808'
                    , 'target-arrow-color': '#080808'
                    , }
            }, {
                selector: 'edge[type="yes"]'
                , style: {
                    'line-color': '#48FF00'
                    , 'target-arrow-color': '#48FF00'
                    , }
            }, {
                selector: 'edge[type="no"]'
                , style: {
                    'line-color': '#ED1000'
                    , 'target-arrow-color': '#ED1000'
                    , }
            }
            , {
                selector: ':selected'
                , style: {}
            }]

    });
    var handles = new CytoscapeEdgeEditation;
    handles.init(cy);
    handles.registerHandle({
        positionX: "left"
        , positionY: "center"
        , color: "#D8D8D8"
        , type: "default"
        , single: false
        , nodeTypeNames: ["type1", "default"]
    });
    handles.registerHandle({
        positionX: "left"
        , positionY: "center"
        , color: "#48FF00"
        , type: "yes"
        , single: true
        , nodeTypeNames: ["type2"]
    });
    handles.registerHandle({
        positionX: "right"
        , positionY: "center"
        , color: "#ED1000"
        , type: "no"
        , single: true
        , nodeTypeNames: ["type2", "default"]
    });
    //ADJUST HEIGHT
    var resizeViewport = function () {
        $(".cy").height($(window).height());
        cy.resize();
    };
    $(window).resize(resizeViewport);
    resizeViewport();
    //getData();
    getComList();
    document.getElementById("sgName").value = json.returnobject.serviceGraph.descriptiveSGMetadata.sgname;
    //LAYOUT
    cy.layout({
        name: 'random'
        , fit: true
        , padding: 10
        , boundingBox: {
            x1: -100
            , y1: -100
            , x2: 100
            , y2: 100
        }
    });
    cy.nodes().on("click", function () {
        displayNodeInfo(this);
    });
    cy.nodes().on("cxttap", function () {
        removeElem(this);
    });
    cy.$('edge').on("cxttap", function () {
        removeElem(this);
    });
}

function displayNodeInfo(node) {
    var selectNodeID = node.data('id');
    var selectNodeName = node.data('name');
    var selectedNodeCNID = node.data('cnid');
    ;
    /*for (i in nodes) {
     if (nodes[i].cnid == selectNodeID) {
     selectedNodeCNID = nodes[i].cnid;
     }
     }*/
    //console.log("sssssssssssssssssss"+ JSON.stringify(json.returnobject.componentList));
    $.each(json.returnobject.componentList, function (i, obj) {
        if (obj) {
            if (obj.descriptiveCNMetadata.cnid == selectedNodeCNID) {
                //console.log(JSON.stringify(obj));
                document.getElementById("nodeCNID").value = selectedNodeCNID;
                var text = "<p> <u> CNID: </u>"+selectedNodeCNID+"<br/>"+
                           " <u> Description: </u>"+obj.descriptiveCNMetadata.description+"<br/>"+
                           "<u> Version: </u>"+obj.descriptiveCNMetadata.version +"</p>";

                $("#info").html(text);
                 $("#info").show();
                /*document.getElementById("txtarea").value = JSON.stringify(obj);
                document.getElementById("nodeDescription").value = obj.descriptiveCNMetadata.description;
                document.getElementById("nodeVersion").value = obj.descriptiveCNMetadata.version;
                document.getElementById("nodeCNname").value = obj.descriptiveCNMetadata.cnname;
                if (obj.descriptiveCNMetadata.tags) {
                    document.getElementById("nodeTags").value = JSON.stringify(obj.descriptiveCNMetadata.tags.tag);
                } else {
                    document.getElementById("nodeTags").value = "undefined";
                }*/
                if (obj.requiredChainableEndpoints && obj.exposedChainableEndpoints){
                    var output = "<label>Select Link (required): </label> <select class='form-control' id='exposedChainableEndpoints'  onchange='changeInterface(this)'>"
                    output = output+"<option>select interface</option>"
                    for(var i=0; i<obj.requiredChainableEndpoints.chainableEndpointCategory.length; i++){
                        output = output + " <option value='"+ obj.requiredChainableEndpoints.chainableEndpointCategory[i].cepcid+"'>"+ obj.requiredChainableEndpoints.chainableEndpointCategory[i].cepcid +"</option>";
                    }
                    output =output + "</select> <br/>";
                    $("#request").html(output);
                }else{
                    $("#request").empty();

                }
                return false;
            }
            //else if () {}
            else {
                  $("#info").hide();
                  document.getElementById("nodeCNID").value = "undefined";
//                document.getElementById("txtarea").value = "undefined";
//                document.getElementById("nodeDescription").value = "undefined";
//                document.getElementById("nodeVersion").value = "undefined";
              //  document.getElementById("nodeCNname").value = "undefined";
            }
        }
    });

    document.getElementById("nodeID").value = selectNodeID;
    document.getElementById("nodeName").value = selectNodeName;
    //alert(selectNodeID);
}

function changeInterface(){
      var e = document.getElementById("exposedChainableEndpoints");
         var compName = e.options[e.selectedIndex].text;
         var compID = e.options[e.selectedIndex].value;

      index=0;
      var otherSelect = "<label>Select compatible Interface</label> <select id='requiredSelect' class='form-control' id='otherSelect' >"
      otherSelect = otherSelect+"<option>select interface</option>"
      $.each(json.returnobject.componentList, function (i, obj){
              if (obj.exposedChainableEndpoints) {
                for(var i=0; i<obj.exposedChainableEndpoints.exposedChainableEndpoint.length; i++){
                    if((obj.exposedChainableEndpoints.exposedChainableEndpoint[i].ecepid != compID) && (obj.exposedChainableEndpoints.exposedChainableEndpoint[i].chainableEndpointCategory.cepcid===compName)){
                        otherSelect = otherSelect + " <option value='"+ obj.descriptiveCNMetadata.cnid+"'> Interface"+(index+1) + " "+ obj.exposedChainableEndpoints.exposedChainableEndpoint[i].chainableEndpointCategory.cepcid + "</option>";
                        index++;
                    }
                }
              }

      });


      otherSelect =otherSelect + "</select> +  <button  type='button' class='btn btn-success btn-block' onclick='createConnection()'>Create Link</button>";
      $("#request").append(otherSelect);
}

function createConnection(){

    var e = document.getElementById("exposedChainableEndpoints");
    var sourceCNID = $("#nodeCNID").val()
    var sourceNID = $("#nodeID").val();

    var ex = document.getElementById("requiredSelect");
    var targetCNID = ex.options[ex.selectedIndex].value;
    if(targetCNID==='select'){
        alert("select one other option");
    }
    else{
        var position=ex.selectedIndex-1;

        var table=[];
        for(var i=0; i<json.returnobject.serviceGraph.graphNodeDescriptor.graphNode.length; i++){
           if(json.returnobject.serviceGraph.graphNodeDescriptor.graphNode[i].cnid===targetCNID){
            table.push(json.returnobject.serviceGraph.graphNodeDescriptor.graphNode[i].nid);
           }
        }

        var targetNID = table[position];

        var drawnewEdge = newEdgeValidation(sourceCNID, sourceNID, targetCNID, targetNID);
        if(drawnewEdge){
             if(document.getElementById("sgid").value != -1){
                       guid = document.getElementById("sgid").value;
                }else{
                    guid = (S4() + S4() + "-" + S4() + "-4" + S4().substr(0, 3) + "-" + S4() + "-" + S4() + S4() + S4()).toLowerCase();
                }
                json.returnobject.serviceGraphJson = JSON.stringify(json.returnobject.serviceGraph);
                json.returnobject.serviceGraph.descriptiveSGMetadata.sgid = guid;
                json.returnobject.serviceGraph.descriptiveSGMetadata.sgName = document.getElementById("sgName").value;
                if (!checkifNodesAdded()) {
                    $.post("/api/v1/servicegraph/registerJson", {
                        json: JSON.stringify(json.returnobject.serviceGraph)
                    }).success(function (data) {
                        page.redirect("/application/"+guid);
                    });
                }
        }else{
            alert("cannot create this connection ");
        }
    }
}


function changeNodeName() {
    var selectNodeID = document.getElementById("nodeID").value;
    var selectNodeName = document.getElementById("nodeName").value;
    var node = cy.nodes("[id=\"" + selectNodeID + "\"]");
    node.data('name', selectNodeName);
    //alert(selectNodeName);
}

function removeElem(edge) {
    edge.remove();
}

function refresh() {
    cy.nodes().on("click", function () {
        displayNodeInfo(this);
    });
    cy.nodes().on("cxttap", function () {
        removeElem(this);
    });
    cy.$('edge').on("cxttap", function () {
        removeElem(this);
    });
    var tappedBefore;
    var tappedTimeout;
    cy.on('tap', function (event) {
        var tappedNow = event.cyTarget;
        if (tappedTimeout && tappedBefore) {
            clearTimeout(tappedTimeout);
        }
        if (tappedBefore === tappedNow) {
            tappedNow.trigger('doubleTap');
            tappedBefore = null;
        } else {
            tappedTimeout = setTimeout(function () {
                tappedBefore = null;
            }, 300);
            tappedBefore = tappedNow;
        }
    });
    cy.nodes().on('doubleTap', function (event) {
        var reNodeCNID = event.cyTarget.data('cnid');
        var reNodeID = event.cyTarget.data('id');
        console.log(event);
        event.stopPropagation ? event.stopPropagation() : (event.cancelBubble = true)
        //event.stopPropagation();
        if (confirm('Are you sure you want to remove ' + reNodeID + ' node from the graph?')) {
            event.cyTarget.remove();
            reNodeSerGraph(reNodeCNID, reNodeID);
        } else {
            // Do nothing!
        }
    });
    var layout = cy.makeLayout({
        name: 'breadthfirst'
    });
    layout.run();
}

function refresh2() {
    cy.nodes().on("click", function () {
        displayNodeInfo(this);
    });
    cy.nodes().on("cxttap", function () {
        removeElem(this);
    });
    cy.$('edge').on("cxttap", function () {
        removeElem(this);
    });
    var tappedBefore;
    var tappedTimeout;
    cy.on('tap', function (event) {
        var tappedNow = event.cyTarget;
        if (tappedTimeout && tappedBefore) {
            clearTimeout(tappedTimeout);
        }
        if (tappedBefore === tappedNow) {
            tappedNow.trigger('doubleTap');
            tappedBefore = null;
        } else {
            tappedTimeout = setTimeout(function () {
                tappedBefore = null;
            }, 300);
            tappedBefore = tappedNow;
        }
    });
    cy.nodes().on('doubleTap', function (event) {
        var reNodeCNID = event.cyTarget.data('cnid');
        var reNodeID = event.cyTarget.data('id');
        if (confirm('Are you sure you want to remove ' + reNodeID + ' node from the graph?')) {
            event.cyTarget.remove();
            reNodeSerGraph(reNodeCNID, reNodeID);
        } else {
            // Do nothing!
        }
    });
}

function S4() {    
    return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
}

function getRandomInt() {
    return Math.floor(Math.random() * (500 - 10 + 1)) + 10;
}

function updateCompListMenu() {
    $('#componentsList').find('option').remove().end();
    var menuContent = document.getElementById("componentsList");
    menuContent.remove(0);
    $.each(comList, function (i, obj) {
        // menuContent += "<option  value="+obj.component.descriptiveCNMetadata.cnid+">" + obj.component.descriptiveCNMetadata.cnname + "</option>";
        var opt = document.createElement('option');
        opt.text = obj.component.descriptiveCNMetadata.cnname;
        opt.value = obj.component.descriptiveCNMetadata.cnid;
        menuContent.add(opt, null);
    });
    //document.getElementById("componentsList").value = menuContent;
}

function addNewComp() {
    guid = (S4() + S4() + "-" + S4()).toLowerCase();
    var e = document.getElementById("componentsList");
    var compName = e.options[e.selectedIndex].text;
    var compID = e.options[e.selectedIndex].value;
    var flag = updateGraphJSON(compID, guid);
    if (flag) {
        cy.add({
            group: "nodes"
            , data: {
                id: guid
                , type: "type1"
                , cnid: compID
                , name: compName + " (" + guid +")"
            }
            , position: {
                x: getRandomInt()
                , y: getRandomInt()
            }
            , });
        refresh2();
        cy.fit();
    } else {
    }
}

function updateGraphJSON(cnid, guid) {
    var flag = false;
    if (json.returnobject.componentList.length > 0) {
//        loop1: for (var i = 0; i < json.returnobject.componentList.length; i++) {
//            if (json.returnobject.componentList[i]) {
//                var obj = json.returnobject.componentList[i];
//                if (obj.descriptiveCNMetadata.cnid == cnid) {
//                    alert("Already Included");
//                    flag = false;
//                    break loop1;
//                } else {
//                    flag = true;
//                }
//            }
//        }
//        if (flag) {
            loop2: for (var j = 0; j < comList.length; j++) {
                var obj2 = comList[j];
                if (obj2.component.descriptiveCNMetadata.cnid == cnid) {
                    json.returnobject.componentList.push(obj2.component);
                    flag = true;
                    var chknodeCNID = cnid;
                    var chknodeNID = guid;
                    var newnodeObj = {
                        "nid": chknodeNID
                        , "cnid": chknodeCNID
                        , "graphDependency": []
                    }
                    json.returnobject.serviceGraph.graphNodeDescriptor.graphNode.push(newnodeObj);
                    break loop2;
                }
            }
        //}
    } else {
        loop2: for (var j = 0; j < comList.length; j++) {
            var obj2 = comList[j];
            if (obj2.component.descriptiveCNMetadata.cnid == cnid) {
                var chknodeCNID = cnid;
                var chknodeNID = guid;
                var newnodeObj = {
                    "nid": chknodeNID
                    , "cnid": chknodeCNID
                    , "graphDependency": []
                }
                json.returnobject.serviceGraph.graphNodeDescriptor.graphNode.push(newnodeObj);
                json.returnobject.componentList.push(obj2.component);
                flag = true;
                break loop2;
            }
        }
    }
    return flag;
}


var containsCEPCID = function _isContains(json, value) {
     var contains = false;
    //noinspection JSAnnotator
    Object.keys(json).some(function(key) {
        contains = typeof json[key] === 'object' ? _isContains(json[key], value) : json[key] === value;
        return contains;
    });
    return contains;
};


function newEdgeValidation(sourceCNID, sourceNID, targetCNID, targetNID) {
    //console.log("sourceCNID: " + sourceCNID + " targetCNID: " + targetCNID)
    console.log("source--> "+sourceCNID+" targets--> "+targetCNID);
    console.log("source--> "+sourceNID+" targets--> "+targetNID);
    var drawnewEdge = false;
    snapshot = Defiant.getSnapshot(json);
    var sourceObj = JSON.search(snapshot, '//componentList/*[cnid=\'' + sourceCNID + '\']/..');
    var targetObj = JSON.search(snapshot, '//componentList/*[cnid=\'' + targetCNID + '\']/..');

    if (sourceObj[0].requiredChainableEndpoints != null) {
        if (targetObj[0].exposedChainableEndpoints != null) {

            var reCEPCID = JSON.search(sourceObj, "//requiredChainableEndpoints/chainableEndpointCategory");
            var exCEPCID = JSON.search(targetObj, "//exposedChainableEndpoints/exposedChainableEndpoint[chainableEndpointCategory]");

//          if (reCEPCID[0].cepcid == exCEPCID[0].chainableEndpointCategory.cepcid) {
            //console.log("contains: "+  exCEPCID[0].chainableEndpointCategory.cepcid + " ? "      + containsCEPCID(reCEPCID,exCEPCID[0].chainableEndpointCategory.cepcid))

            if (containsCEPCID(reCEPCID,exCEPCID[0].chainableEndpointCategory.cepcid)) {
                drawnewEdge = true;
                var sourceNodeSerGraph = JSON.search(json, "//serviceGraph/graphNodeDescriptor/graphNode[cnid=\"" + sourceCNID + "\"]/graphDependency");
                if (Object.keys(sourceNodeSerGraph).length > 0) {
                    //Add the new dependency to the service graph
                    var newDepObj = {
                        cepcid: exCEPCID[0].chainableEndpointCategory.cepcid//reCEPCID[0].cepcid
                        , ecepid: exCEPCID[0].ecepid
                        , nid: targetNID
                    };
                    loop11: for (var i = 0; i < json.returnobject.serviceGraph.graphNodeDescriptor.graphNode.length; i++) {
                        if (json.returnobject.serviceGraph.graphNodeDescriptor.graphNode[i].nid == sourceNID) {
                            json.returnobject.serviceGraph.graphNodeDescriptor.graphNode[i].graphDependency.push(newDepObj);
                            break loop11;
                        }
                    }
                    var targetNodeSerGraph = JSON.search(json, "//serviceGraph/graphNodeDescriptor/graphNode[cnid=\"" + targetCNID + "\"]");
                    if (Object.keys(targetNodeSerGraph).length < 1) {
                        var newtargetnodeObj = {
                            "nid": targetNID
                            , "cnid": targetCNID
                            , "graphDependency": []
                        }
                        json.returnobject.serviceGraph.graphNodeDescriptor.graphNode.push(newtargetnodeObj);
                    }
                } else {
                    //Add new source node
                    var newSourceDepObj = {
                        "nid": sourceNID
                        , "cnid": sourceCNID
                        , graphDependency: [{
                                cepcid: exCEPCID[0].chainableEndpointCategory.cepcid//reCEPCID[0].cepcid
                                , ecepid: exCEPCID[0].ecepid
                                , nid: targetNID
                            }]
                    }
                    json.returnobject.serviceGraph.graphNodeDescriptor.graphNode.push(newSourceDepObj);
                    var targetNodeSerGraph = JSON.search(json, "//serviceGraph/graphNodeDescriptor/graphNode[cnid=\"" + targetCNID + "\"]");
                    if (Object.keys(targetNodeSerGraph).length < 1) {
                        var newtargetnodeObj = {
                            "nid": targetNID
                            , "cnid": targetCNID
                            , "graphDependency": []
                        }
                        json.returnobject.serviceGraph.graphNodeDescriptor.graphNode.push(newtargetnodeObj);
                    }
                }
            } else {
                alert("CEPCID not matching");
            }
        } else {
            alert("This node does not exposed to chainable endpoints");
        }
    } else {
        alert("This node does not require any chainable endpoints");
    }
    return drawnewEdge;
}

function reNodeSerGraph(reNodeCNID, reNodeID) {
    loop12: for (var i = 0; i < json.returnobject.serviceGraph.graphNodeDescriptor.graphNode.length; i++) {
        if (json.returnobject.serviceGraph.graphNodeDescriptor.graphNode[i]) {
            if (json.returnobject.serviceGraph.graphNodeDescriptor.graphNode[i].cnid == reNodeCNID) {
                console.log('i ->' + i + 'node cnid ->' + reNodeCNID);
                //delete json.returnobject.serviceGraph.graphNodeDescriptor.graphNode[i];
                json.returnobject.serviceGraph.graphNodeDescriptor.graphNode.splice(i, 1);
            }
        }
        if (json.returnobject.serviceGraph.graphNodeDescriptor.graphNode[i]) {
            if (json.returnobject.serviceGraph.graphNodeDescriptor.graphNode[i].graphDependency.length > 0) {
                loop13: for (var j = 0; j < json.returnobject.serviceGraph.graphNodeDescriptor.graphNode[i].graphDependency.length; i++) {
                    if (json.returnobject.serviceGraph.graphNodeDescriptor.graphNode[i].graphDependency[j].nid == reNodeID) {
                        console.log('i ->' + i + 'node id ->' + reNodeID);
                        //delete json.returnobject.serviceGraph.graphNodeDescriptor.graphNode[i].graphDependency[j];
                        json.returnobject.serviceGraph.graphNodeDescriptor.graphNode[i].graphDependency.splice(j, 1);
                    }
                }
            }
        }
    }
    loop14: for (var k = 0; k < json.returnobject.componentList.length; k++) {
        if (json.returnobject.componentList[k]) {
            if (json.returnobject.componentList[k].descriptiveCNMetadata.cnid == reNodeCNID) {
                json.returnobject.componentList.splice(k, 1);
                console.log('k ->' + k + 'node cnid ->' + reNodeCNID);
                //delete json.returnobject.componentList[k];
            }
        }
    }
}

function checkifNodesAdded() {
    var chkREQ = false;
    if (cy.nodes().length > 1) {
        cy.nodes().forEach(function (ele) {
            var chknodeCNID = ele.data('cnid');
            if (ele.data('cnid')) {
                console.log(ele.data('cnid'));
                var nodeSerGraph = JSON.search(json, "//returnobject/serviceGraph/graphNodeDescriptor/graphNode[cnid=\"" + chknodeCNID + "\"]");
                console.log(nodeSerGraph[0]);
                snapshot = Defiant.getSnapshot(json);
                var nodeObj = JSON.search(snapshot, '//componentList/*[cnid=\'' + chknodeCNID + '\']/..');
                if (nodeObj[0].requiredChainableEndpoints != null) {
                    //need to be connected to other node
                    if (nodeSerGraph[0].graphDependency.length > 0) {
                        chkREQ = false;
                    } else {
                        alert('This node should be connected to one interface at least');
                        chkREQ = true;
                    }
                }
            }
        });
    } else {
        alert('This Graph is empty please add a new component');
        chkREQ = true;
    }
    return chkREQ;
}

function saveJSON() {

    if(document.getElementById("sgid").value != -1){
           guid = document.getElementById("sgid").value;
    }else{
        guid = (S4() + S4() + "-" + S4() + "-4" + S4().substr(0, 3) + "-" + S4() + "-" + S4() + S4() + S4()).toLowerCase();
    }
    json.returnobject.serviceGraphJson = JSON.stringify(json.returnobject.serviceGraph);
    json.returnobject.serviceGraph.descriptiveSGMetadata.sgid = guid;
    json.returnobject.serviceGraph.descriptiveSGMetadata.sgName = document.getElementById("sgName").value;
    if (!checkifNodesAdded()) {
        $.post("/api/v1/servicegraph/registerJson", {
            json: JSON.stringify(json.returnobject.serviceGraph)
        }).success(function (data) {
            page.redirect("/application/template");
            arcadia.notify("Application template saved");
        });
    }
}

function exportJSON() {
    guid = (S4() + S4() + "-" + S4() + "-4" + S4().substr(0, 3) + "-" + S4() + "-" + S4() + S4() + S4()).toLowerCase();
    json.returnobject.serviceGraphJson = JSON.stringify(json.returnobject.serviceGraph);
    json.returnobject.serviceGraph.descriptiveSGMetadata.sgid = guid;
    json.returnobject.serviceGraph.descriptiveSGMetadata.sgName = document.getElementById("sgName").value;
    var url = 'data:text/json;charset=utf8,' + encodeURIComponent(JSON.stringify(json));
    window.open(url, '_blank');
    window.focus();
}

// Alchemy
function alchemyInit() {
    logger.d("alchemyInit()");


}

/*
 *  Actions
 */

function getElement(e) {
    //var e = nodesList[i];
    if (e  instanceof HTMLInputElement && e.type === 'text') {
        //form[e.id] = e.value;
        return e.value;
    } else if (e  instanceof HTMLInputElement && (e.type === 'checkbox' || e.type === 'radio')) {
        //form[e.id] = e.checked;
        return e.checked;
    } else if (e  instanceof HTMLSelectElement) {
        //form[e.id] = e.value;
        return e.value;
    } else if (e  instanceof HTMLTextAreaElement) {
        //form[e.id] = e.value;
        return e.value;
    }
}