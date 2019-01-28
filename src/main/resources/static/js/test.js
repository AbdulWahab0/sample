
    $.getJSON("/newastridgraph", function (data) {
     document.addEventListener('DOMContentLoaded', function(){
              console.log(data);
              var cy = cytoscape({
                  container: document.getElementById('cy'),
                  elements: data,
                  style: [
                {
                  selector: 'node',
                  style: {

                   'width': '35px',
                   'font-size':'35px',
                   'height': '35px',
                  'content': 'data(name)'
                  }
                },

                {
                  selector: 'edge',
                  style: {
                    'curve-style': 'bezier',
                    'width':'1.3',
                    'arrow-scale':'1.2',
                    'target-arrow-shape': 'triangle'
                  }
                },


                // some style for the extension
                {
                  selector: '.eh-handle',
                  style: {
                    'background-color': 'red',
                    'width': 20,
                    'height': 20,
                    'font-size':'15px',
                    'shape': 'ellipse',
                    'overlay-opacity': 0,
                    'border-width': 2, // makes the handle easier to hit
                    'border-opacity': 0
                  }
                },
                {
                  selector: '.eh-hover',
                  style: {
                    'background-color': 'red',
                    'width': '5px',
                    'height': '5px'

                  }
                },
                {
                  selector: '.eh-source',
                  style: {
                    'border-width': 1,


                  }
                },
                {
                  selector: '.eh-target',
                  style: {
                    'border-width': 1

                  }
                },
                {
                  selector: '.eh-preview, .eh-ghost-edge',
                  style: {
                    'background-color': 'red',
                    'line-color': 'black',

                    'target-arrow-color': 'red',
                    'source-arrow-color': 'red'
                  }
                },
                {
                  selector: '.eh-ghost-edge.eh-preview-active',
                  style: {
                  'border-width': 1,
                    'opacity': 0,
                  }
                }
              ],
                  layout: {
                      name: 'circle'
                  }
              });
              cy.nodes().style({"font-size": 14});
            var eh = cy.edgehandles({
            snap: true
            });

          });
           });
