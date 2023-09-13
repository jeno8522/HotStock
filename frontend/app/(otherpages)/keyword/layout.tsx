import localfont from "next/font/local";

interface Props {
  children: React.ReactNode;
}

const Cafe24SsurroundAir = localfont({
  src: [
    {
      path: "../../../public/fonts/Cafe24SsurroundAir.ttf",
      weight: "normal",
      style: "normal",
    },
  ],
});

export default function KeywordDetailLayout({
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
