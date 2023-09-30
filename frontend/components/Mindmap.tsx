"use client";

import { Stock } from "@/types";
import React, { useEffect, useRef } from "react";
import cytoscape from "cytoscape";
import coseBilkent from "cytoscape-cose-bilkent";
import StockInfo from "@/components/stock/StockInfo";
import "../public/fonts/font.css";

cytoscape.use(coseBilkent);

interface Theme {
  name: string;
}

interface StockProps {
  stock: Stock;
  themes: Theme[];
}

const Mindmap = ({ stock, themes }: StockProps) => {
  const { name } = stock;
  const container = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const themeStrings = themes.map((theme) => theme.name);

    const elements = [
      { data: { id: name, label: name, type: "cur" } },
      ...themes.map((theme) => ({
        data: { id: theme.name, label: theme.name, type: "themes" },
      })),
      ...themes.map((theme) => ({
        data: {
          id: `e_${name}_${theme.name}`,
          source: name,
          target: theme.name,
        },
      })),
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
          selector: "node[type='cur']",
          style: {
            content: "data(label)",
            width: 25,
            height: 25,
            // borderColor: "#6666d5",
            backgroundColor: "#6666d5",
            // "border-opacity": "1",
            // "border-width": "2px",
            "text-outline-color": "#4a4ad3",
            "text-outline-width": "3px",
            color: "white",
            fontSize: 30,
            fontFamily: "Cafe24SsurroundAir",
          },
        },
        {
          selector: "node[type='themes']",
          style: {
            content: "data(label)",
            width: 22,
            height: 22,
            fontSize: 25,
            borderColor: "black",
            backgroundColor: "#ccccea",
            // "text-outline-color": "#a3a3da",
            // "text-outline-width": "3px",
            color: "#8a8ac5",
            fontWeight: "bold",
            fontFamily: "Cafe24SsurroundAir",
          },
        },
        {
          selector: "edge",
          style: {
            width: "2px",
            "target-arrow-shape": "triangle",
            "line-color": "#d7d7ee",
            // "background-color": "purple",
            "border-opacity": 1,
            // "border-width": "10px",
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
