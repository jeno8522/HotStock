import type { Metadata } from "next";

interface Props {
  children: React.ReactNode;
}

export const metadata: Metadata = {
  title: "About Us - Hot Stock : 키워드로 찾는 주식",
  description:
    "실시간 뉴스를 종합한 키워드로 당신을 위한 주식 종목을 찾아드려요.",
};
export default function AboutUsLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <>
      <div>
        <div>{children}</div>
      </div>
    </>
  );
}
