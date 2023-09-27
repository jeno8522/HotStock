import {Theme} from "@/types";
import {fetchThemes} from "@/utils";
import ThemeBar from "@/components/theme/ThemeBar";
const Themelist = async ({
  params,
}: {
  params: {key: string; themeId: string};
}) => {
  const themesResult = await fetchThemes();

  const themeList = themesResult;

  // console.log(themeList);

  const themeIsEmpty =
    !Array.isArray(themeList) || themeList.length < 1 || !themeList;

  // ----------------------------------------
  return (
    <div className="items-center">
      <div>
        {!themeIsEmpty ? (
          <div>
            {themeList.map((theme) => (
              <ThemeBar
                key={theme.themeId}
                themeId={theme.themeId}
                name={theme.name}
              />
            ))}
          </div>
        ) : (
          <div>테마 혹은 이 테마에 해당하는 종목이 존재하지 않습니다.</div>
        )}
      </div>
    </div>
  );
};

export default Themelist;
