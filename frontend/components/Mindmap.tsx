"use client";

import { Stock } from "@/types";
import React, { useEffect, useRef } from "react";
// import { render } from "react-dom";
import cytoscape from "cytoscape";
import coseBilkent from "cytoscape-cose-bilkent"; // Import the cose-bilkent layout extension
import StockInfo from "@/components/stock/StockInfo"; // Import the StockInfo component



cytoscape.use(coseBilkent); // Register the cose-bilkent layout extension

interface StockProps {
  stock: Stock;
}

export const Mindmap = ({stock}: StockProps) => {
  const {
    name,
    code,
    market_sum,
    price_now,
    price_high,
    price_low,
    price_rate,
    amount,
  } = stock;

  const container = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const config = {
      container: container.current,
      layout: {
        name: "cose-bilkent", // Use the cose-bilkent layout
        idealEdgeLength: 100,
        nodeDimensionsIncludeLabels: true,
        randomize: true,
      },
      style: [
        {
          selector: "node",
          style: {
            content: "data(id)",
            "border-color": "black",
            "background-color": "gray",
            "border-opacity": "1",
            "border-width": "1px",
          },
        },
        {
          selector: "edge",
          style: {
            width: "2px",
            "target-arrow-shape": "triangle",
            "line-color": "pink", // Changed from "border-color" to "line-color"
            "background-color": "purple",
            "border-opacity": "1",
            "border-width": "10px",
          },
        },
      ],
      elements: [
        { data: { id: name } }, // Use StockInfo component here
        { data: { id: "테마1" } },
        { data: { id: "테마2" } },
        { data: { id: "테마3" } },
        { data: { id: "테마4" } },
        { data: { id: "테마5" } },
        { data: { id: "e1", source: name, target: "테마1" } },
        { data: { id: "e2", source: name, target: "테마2" } },
        { data: { id: "e3", source: name, target: "테마3" } },
        { data: { id: "e4", source: name, target: "테마4" } },
        { data: { id: "e5", source: name, target: "테마5" } },
      ],
    };

    const cy = cytoscape(config);

    // You can optionally fit the graph to the container after the layout is applied
    cy.fit();

    // If you want to disable user panning and zooming, you can add the following:
    // cy.userZoomingEnabled(false);
    // cy.userPanningEnabled(false);
  }, []);

  return (
    <div>
      <div ref={container} style={{ height: "500px" }} />
    </div>
  );
};

// render(<Mindmap />, document.getElementById("root"));

export default Mindmap;