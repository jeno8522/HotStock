"use client"

import { Stock } from "@/types";
import React, { useEffect, useRef } from "react";
import cytoscape from "cytoscape";
import coseBilkent from "cytoscape-cose-bilkent";
import StockInfo from "@/components/stock/StockInfo";

cytoscape.use(coseBilkent);

interface Theme {
  name: string;
}

interface StockProps {
  stock: Stock;
  themes: Theme[];
}

export const Mindmap = ({ stock, themes }: StockProps) => {
  const { name } = stock;
  const container = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const themeStrings = themes.map((theme) => theme.name);

    const elements = [
      { data: { id: name, label: name } },
      ...themes.map((theme) => ({ data: { id: theme.name, label: theme.name } })),
      ...themes.map((theme) => ({ data: { id: `e_${name}_${theme.name}`, source: name, target: theme.name } })),
    ];

    const config = {
      container: container.current,
      layout: {
        name: "cose-bilkent",
        idealEdgeLength: 100,
        nodeDimensionsIncludeLabels: true,
        randomize: true,
      },
      style: [
        {
          selector: "node",
          style: {
            content: "data(label)",
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
            "line-color": "pink",
            "background-color": "purple",
            "border-opacity": "1",
            "border-width": "10px",
          },
        },
      ],
      elements,
    };

    const cy = cytoscape(config);
    cy.fit();
  }, [stock, themes]);

  return (
    <div>
      <div ref={container} style={{ height: "500px" }} />
    </div>
  );
};

export default Mindmap;
