import { FormGroup } from '@angular/forms';
import { Component, OnInit ,ViewEncapsulation} from '@angular/core';
import * as d3 from 'd3';
interface Node {
  id: string;
  group: number;
}

interface Link {
  source: string;
  target: string;
  value: number;
}

interface Graph {
  nodes: Node[];
  links: Link[];
}
@Component({
  selector: 'app-d3-app',
  templateUrl: './d3-app.component.html',
  styleUrls: ['./d3-app.component.css'],
  encapsulation:ViewEncapsulation.None
})
export class D3AppComponent implements OnInit {
  ngOnInit() {
    console.log('D3.js version:', d3['version']);
    
    d3.select("body").style("background-color", "");

    const svg = d3.select('svg');
    const width = +svg.attr('width');
    const height = +svg.attr('height');
   
    const color = d3.scaleOrdinal(d3.schemeCategory10);

    const simulation = d3.forceSimulation()
      .force('link', d3.forceLink().id((d: any) => d.id))
      .force('charge', d3.forceManyBody().strength(80).distanceMax(0).distanceMin(80))
      .force('center', d3.forceCenter(width/2 , height/2 ))
      .force('collide', d3.forceCollide(function(d:any){
        return d.id === "j" ? 20 : 40
    }));
//. force("link",d3.forceLink().distance(function (d:any){return d.target.distance*100;}));
  //var force=d3.layout.force().linkDistance(function(d){return d.distance*100;})
   
      d3.json('assets/miserables.json')
    .then((data) => {
        // Use data
        const nodes: Node[] = [];
      const links: Link[] = [];

      data.nodes.forEach((d) => {
        nodes.push(<Node>d);
      });

      data.links.forEach((d) => {
        links.push(<Link>d);
      });
    /*  var simulation = d3.forceSimulation()
    .force("link", d3.forceLink().distance(function(d) {return 100;}).strength(0.1))*/
      const graph: Graph = <Graph>{ nodes, links };
      svg.append('defs').append('marker')
      .attr('id','arrowhead')
      .attr('viewBox','-0 -5 10 10')
      //.attr("viewBox", "0 0 " + width + " " + height )
      .attr( 'refX',13)
         .attr( 'refY',0)
          .attr('orient','auto')
          .attr('markerWidth',13)
          .attr('markerHeight',13)
          .attr('xoverflow','visible')
      .append('svg:path')
      .attr('d', 'M 0,-5 L 10 ,0 L 0,5')
      .attr('fill', 'black')
      .style('stroke','none');
    
     const link = svg.append('g')
        .attr('class', 'links')
        .selectAll('line')
        .data(graph.links)
        .enter()
        .append('line')
        .attr('marker-end','url(#arrowhead)')
      .attr('linkDistance',100000);
      const node = svg.append('g')
        .attr('class', 'nodes')
        .selectAll('circle')
        .data(graph.nodes)
        .enter()
        .append("g")
        var circles = node.append("circle")
        .attr("r", 20)
        .attr("fill", function(d:any) { return color(d.group)})
        .call(d3.drag()
            .on("start", dragstarted)
            .on("drag", dragged)
            .on("end", dragended));
  
        var lables = node.append("text")
        .text(function(d) {
          return d.id;
        })
        .attr('x',14)
        .attr('y',28);
  
      /*svg.selectAll('circle').call(d3.drag()
        .on('start', dragstarted)
        .on('drag', dragged)
        .on('end', dragended)
      );*/

      node.append('title')
        .text((d:any) => d.id);

      simulation
        .nodes(data.nodes)
        .on('tick', ticked);

      simulation.force<d3.ForceLink<any, any>>('link')
        .links(graph.links);
      

      function ticked() {
        link
          .attr('x1', function(d: any) { return d.source.x; })
          .attr('y1', function(d: any) { return d.source.y; })
          .attr('x2', function(d: any) { return d.target.x; })
          .attr('y2', function(d: any) { return d.target.y; });
         node
          .attr("transform", function(d:any) {
            return "translate(" + d.x + "," + d.y + ")";
          })
        node
          .attr('cx', function(d: any) { return d.x; })
          .attr('cy', function(d: any) { return d.y; });
      }
    

    function dragstarted(d) {
      if (!d3.event.active) { simulation.alphaTarget(0.3).restart(); }
      d.fx = d.x;
      d.fy = d.y;
    }

    function dragged(d) {
      d.fx = d3.event.x;
      d.fy = d3.event.y;
    }

    function dragended(d) {
      if (!d3.event.active) { simulation.alphaTarget(0); }
      d.fx = null;
      d.fy = null;
    }
  });
  }
}
