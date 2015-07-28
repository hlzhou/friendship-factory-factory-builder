package moments.app;

import android.content.Context;
import android.media.AudioManager;

import java.util.List;
import java.util.Map;
import java.util.Random;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

/**
 * Manages the various questions which can be used to prompt the user to give a happy memory
 * via notification.
 */
public class QuestionManager {

    private static Map<String, String> questionsPromptMap;
    private static List<String> questions;

    // Signifies that a prompt should be appended to the beginning of a user's response
    private static String APPEND_TO_BEGINNING_FLAG = "... ";

    /**
     * Simplifies retrieval of Strings from string resources
     */
    private static class EasyStringGetter {
        private Context context;

        public EasyStringGetter(Context context) {
            this.context = context;
        }

        public String get(int id) {
            return context.getString(id);
        }
    }

    public static void setUpQuestionManager(Context context) {
        if(isSetUp()) {
            return;
        }
        questions = ImmutableList.copyOf(context.getResources().getStringArray(R.array.questions));
        EasyStringGetter esg = new EasyStringGetter(context);
        questionsPromptMap = ImmutableMap.<String, String>builder()
                .put(esg.get(R.string.how_day), esg.get(R.string.how_day_prompt) + APPEND_TO_BEGINNING_FLAG)
                .put(esg.get(R.string.what_listen), esg.get(R.string.what_listen_prompt) + APPEND_TO_BEGINNING_FLAG)
                .build();
    }

    public static boolean isSetUp() {
        return questionsPromptMap != null && questions != null;
    }

    public static class MomentQuestion {
        private String question;
        private String prompt;

        public MomentQuestion(String question) {
            this(question, null);
        }

        public MomentQuestion(String question, String prompt) {
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

    public static boolean doAppendToBeginning(String prompt) {
        if (prompt == null) {
            return false;
        }
        return prompt.contains(APPEND_TO_BEGINNING_FLAG);
    }

    /**
     *
     * @param question
     * @return whether or not the given question is currently applicable to the user.
     */
    public static boolean isQuestionAppropriate(String question, Context context) {
        if (question.equals(context.getString(R.string.what_listen))) {
            AudioManager manager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
            if(manager.isMusicActive()){
                return true;
            }
            return false;
        }
        return true;
    }

    public static MomentQuestion getRandomQuestion(Context context) {
        if (!isSetUp()) {
            setUpQuestionManager(context);
        }
        String question;
        do{
            question = questions.get(new Random().nextInt(questions.size()));
        } while(!isQuestionAppropriate(question, context));
        return new MomentQuestion(question, questionsPromptMap.get(question));
    }

}
