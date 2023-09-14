"use client"

import React, { useEffect } from "react";

import TagCloud from "TagCloud";

const TextSphere = () => {
  useEffect(() => {
    return () => {
      const container = ".tagcloud";
      const texts: Array<string> = [
        "HTML5",
        "CSS3",
        "JavaScript",
        "React",
        "Node.js",
        "Express",
        "MongoDB",
        "Python",
        "Django",
        "C",
        "C++",
        "Java",
        "SQL",
        "Git",
        "GitHub",
        "VS Code",
        "Linux",
        "Windows",
        "MacOS",
        "Arduino",
        "Raspberry Pi",
        "AWS",
        "Heroku",
        "Firebase",
        "Google Cloud",
        "Microsoft Azure",
        "Adobe Photoshop",
        "Adobe Illustrator",
        "Adobe XD",
        "Adobe Premiere Pro",
        "Adobe After Effects",
        "Adobe Lightroom",
        "Adobe InDesign",
        "Figma",
      ];

      const options = {
        radius: 300,
        maxSpeed: "fast",
        initSpeed: "fast",
        direction: 135,
        keep: true,
      };

      TagCloud(container, texts);
    };
  }, []);

  return (
    <>
      <div className="text-shpere">
        <span className="tagcloud"></span>
      </div>
    </>
  );
};

export default TextSphere;
