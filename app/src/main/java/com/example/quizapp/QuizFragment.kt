package com.example.quizapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.data.Statement
import com.example.quizapp.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    private var statementIndex = 0;
    private lateinit var currentStatement: Statement;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = activity?.run {
            ViewModelProvider(this)[SharedViewModel::class.java]
        }


        viewModel?.statements?.observe(viewLifecycleOwner) { statements ->
            getNext(statements);
            binding.btnTrue.setOnClickListener{
                handleStatement(true, statements);
            }
            binding.btnFalse.setOnClickListener{
                handleStatement(false, statements);
            }

        }


     }

    private fun handleStatement(flag: Boolean, statements: List<Statement>) {
        var s: String = "";
        if(currentStatement.isCorrect){
            if(flag)
                s = resources.getString(R.string.correct)
                else
                s = resources.getString(R.string.correct_false, currentStatement.correctStatement)
            } else {
                if (flag)
                    s = resources.getString(
                        R.string.incorrect_true,
                        currentStatement.correctStatement
                    )
                else
                    s = resources.getString(R.string.correct)
            }
            val myToast = Toast.makeText(getContext(), s, Toast.LENGTH_SHORT)
            myToast.show()
            getNext(statements)


            }



    private fun getNext(st:List<Statement>){
        if(statementIndex < st.size){
            currentStatement = st[statementIndex++];
            binding.statementText.text = currentStatement.statement;
        } else {
            binding.statementText.text = resources.getString(R.string.game_over)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}