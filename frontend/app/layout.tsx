import "./globals.css";
import type { Metadata } from "next";
import { Inter } from "next/font/google";
import { Navbar, Footer } from "@/components";
import localfont from "next/font/local";
import { Providers } from "./providers";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "Hot Stock : 키워드로 찾는 주식",
  description: "실시간 뉴스를 종합한 키워드로 당신을 위한 주식 찾기",
};

const NanumBarunGothicLight = localfont({
  src: [
    {
      path: "../public/fonts/NanumBarunGothicLight.ttf",
      weight: "normal",
      style: "normal",
    },
  ],
});

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <body className={`${inter.className} ${NanumBarunGothicLight.className}`}>
        <Providers>
          <Navbar />
          <div className="">{children}</div>
          <Footer />
        </Providers>
      </body>
    </html>
  );
}
