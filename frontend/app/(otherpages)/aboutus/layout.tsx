import type { Metadata } from "next";
import { Inter } from "next/font/google";
import localfont from "next/font/local";

const cafe24SsurroundAir = localfont({
  src: [
    {
      path: "../../../public/fonts/cafe24SsurroundAir.ttf",
      weight: "normal",
      style: "normal",
    },
  ],
});

interface Props {
  children: React.ReactNode;
}

export default function AboutUsLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <>
      <div className={cafe24SsurroundAir.className}>
        <div>{children}</div>
      </div>
    </>
  );
}
