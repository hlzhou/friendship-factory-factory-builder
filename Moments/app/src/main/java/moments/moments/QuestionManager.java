package moments.moments;

import android.content.Context;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class QuestionManager {

    private static Map<String, String> questionsPromptMap;
    private static List<String> questions;

    private class EasyStringGetter {
        private Context context;

        public EasyStringGetter(Context context) {
            this.context = context;
        }

        public String get(int id) {
            return context.getString(id);
        }
    }

    public void setUpQuestionManager(Context context) {
        if(isSetUp()) {
            return;
        }
        questions = ImmutableList.copyOf(context.getResources().getStringArray(R.array.questions));
        EasyStringGetter esg = new EasyStringGetter(context);
        questionsPromptMap = ImmutableMap.<String, String>builder()
                .put(esg.get(R.string.how_day), esg.get(R.string.how_day_prompt))
                .put(esg.get(R.string.what_listen), esg.get(R.string.what_listen_prompt))
                .build();
    }

    public boolean isSetUp() {
        return questionsPromptMap != null && questions != null;
    }

    private class MomentQuestion {
        private String question;
        private String prompt;

        public MomentQuestion(String question) {
            this(question, null);
        }

        public MomentQuestion(String question, String propmt) {
            this.question = question;
            this.prompt = prompt;
        }

        public String getQuestion() {
            return question;
        }

        public String getPrompt() {
            if (prompt == null) {
                return question;
            }
            return prompt;
        }

        public boolean doAppendPrompt() {
            return prompt != null;
        }
    }
    

}