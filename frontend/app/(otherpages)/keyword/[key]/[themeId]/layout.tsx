import type { Metadata } from "next";

interface Props {
  children: React.ReactNode;
}

export const metadata: Metadata = {
  title: "테마 - Hot Stock : 키워드로 찾는 주식",
  description: "다양한 테마로 관련 주식 종목 정보를 조회해보세요.",
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
