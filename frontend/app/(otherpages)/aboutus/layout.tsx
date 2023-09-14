import type { Metadata } from "next";
import { Inter } from "next/font/google";
import localfont from "next/font/local";

const Cafe24SsurroundAir = localfont({
  src: [
    {
      path: "../../../public/fonts/Cafe24SsurroundAir.ttf",
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
      <div className={Cafe24SsurroundAir.className}>
        <div>{children}</div>
      </div>
    </>
  );
}
