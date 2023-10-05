"use client";

import { Stock } from "@/types";
import React, { useEffect, useRef } from "react";
import cytoscape from "cytoscape";
import coseBilkent from "cytoscape-cose-bilkent";
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
            width: "15rem",
            height: "15rem",
            backgroundColor: "#7e7ecd",
            "text-outline-color": "#4a4ad3",
            "text-outline-width": "3px",
            color: "white",
            "font-size": "17vw",
            fontFamily: "Nanum Gothic",
          },
        },
        {
          selector: "node[type='themes']",
          style: {
            content: "data(label)",
            width: "13rem",
            height: "13rem",
            "font-size": "13vw",
            backgroundColor: "#e6e6ef",
            color: "#6d6dab",
            fontWeight: "bold",
            fontFamily: "Nanum Gothic",
          },
        },
        {
          selector: "edge",
          style: {
            width: "2px",
            "target-arrow-shape": "triangle",
            "line-color": "#e6e6ef",
            // "background-color": "purple",
            "border-opacity": 1,
            // "border-width": "10px",
          },
        },
      ],
      elements,
    };

    const cy = cytoscape(config);

    // 마우스 드래그 및 확대/축소 기능 비활성화
    cy.userZoomingEnabled(false);
    cy.userPanningEnabled(false);

    // 레이아웃 재조정 방지
    cy.on("layoutstop", () => {
      cy.fit();
    });

    // 마인드맵 요소들의 드래그 비활성화
    cy.nodes().forEach((node) => {
      node.lock();
    });
  }, [stock, themes, name]);

  return (
    <div>
      <div ref={container} style={{ height: "500px" }} />
    </div>
  );
};

export default Mindmap;
